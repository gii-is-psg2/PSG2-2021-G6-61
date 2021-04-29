package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Confirmation;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.CausaService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/causas/{causaId}")
public class DonationController {

	private DonationService donationService;
	private CausaService causaService;

	@Autowired
	public DonationController(DonationService donationService, CausaService causaService) {
		this.donationService = donationService;
		this.causaService = causaService;
	}

	@ModelAttribute("causa")
	public Causa findCausa(@PathVariable("causaId") final int causaId) {
		return this.causaService.findById(causaId).get();
	}

	@ModelAttribute("donor")
	public String findDonor(Authentication auth) {
		return auth.getName();
	}

	@GetMapping(value = "/newDonation")
	public String newDonation(final ModelMap model) {
		Donation donation = new Donation();
		model.put("donation", donation);
		return "causes/newDonation";
	}

	@PostMapping(value = "/newDonation")
	public String newDonation(@Valid Donation donation, BindingResult result, final ModelMap model,
			final RedirectAttributes redirectAttributes) {

		Causa causa = (Causa) model.getAttribute("causa");
		String donante = (String) model.getAttribute("donor");

		if (result.hasErrors()) {
			return "causes/newDonation/";
		} else if (donationService.findByCausaAndDonante(causa, donante) != null) {

			Donation d = donationService.findByCausaAndDonante(causa, donante);

			if (d.getCantidad() + donation.getCantidad() > causa.getObjetivoPresupuestario()) {

				Double dtot = causa.getObjetivoPresupuestario() - causa.getAcumulado();

				model.put("confirmacion", new Confirmation<Donation>(
						"Ya esta donando " + d.getCantidad().toString()
								+ " euros para esta causa, con la nueva donacion pasara a donar " + dtot.toString()
								+ " puesto que se conseguirá el objetivo de la causa. Desea continuar?",
						"/causas/" + d.getCausa().getId() + "/updateDonation/", donation.getCantidad().toString(), d));

				model.put("donation", d);
				return "causes/newDonation";

			} else {

				Double dtot = d.getCantidad() + donation.getCantidad();
				model.put("confirmacion", new Confirmation<Donation>(
						"Ya esta donando " + d.getCantidad().toString()
								+ " euros para esta causa, con la nueva donacion pasara a donar " + dtot.toString()
								+ ". Desea continuar?",
						"/causas/" + d.getCausa().getId() + "/updateDonation/", donation.getCantidad().toString(), d));

				model.put("donation", d);
				return "causes/newDonation";
			}

		} else {

			double scale = Math.pow(10, 2);
			donation.setCantidad(Math.round(donation.getCantidad() * scale) / scale);
			Double nuevoAcum = donation.getCantidad() + causa.getAcumulado();

			if (nuevoAcum > causa.getObjetivoPresupuestario()) {
				donation.setCantidad(causa.getObjetivoPresupuestario() - causa.getAcumulado());
				redirectAttributes.addAttribute("message", "TargetAchieved");
				causa.setFinalizada(true);
				nuevoAcum = causa.getObjetivoPresupuestario();
			} else
				redirectAttributes.addAttribute("message", "DonationSubmitted");

			causa.setAcumulado(nuevoAcum);
			donation.setCausa(causa);
			donation.setDonante((String) model.getAttribute("donor"));
			donation.setFecha(LocalDate.now());
			donationService.save(donation);
			return "redirect:/causas/{causaId}";
		}

	}

	@GetMapping(value = "/updateDonation/{idDonation}")
	public String updateDonation(@PathVariable("idDonation") int idDonation,
			@RequestParam(name = "data", required = false) String data, final ModelMap model,
			final RedirectAttributes redirectAttributes) {

		Donation donation = donationService.findById(idDonation).get();

		Causa causa = (Causa) model.getAttribute("causa");
		double scale = Math.pow(10, 2);

		donation.setCantidad(Math.round(Double.valueOf(data) * scale) / scale);
		Double nuevoAcum = Double.valueOf(data) + causa.getAcumulado();

		if (donation != null && !StringUtils.isEmpty(data)) {

			if (nuevoAcum > causa.getObjetivoPresupuestario()) {

				donation.setCantidad(causa.getObjetivoPresupuestario() - causa.getAcumulado());
				donationService.save(donation);
				causa.setFinalizada(true);
				nuevoAcum = causa.getObjetivoPresupuestario();
				redirectAttributes.addAttribute("message", "TargetAchieved");
			} else {

				donation.setCantidad(donation.getCantidad() + Double.valueOf(data));
				donationService.save(donation);

				redirectAttributes.addAttribute("message", "DonationSubmitted");
			}

		}

		return "redirect:/causas/" + donation.getCausa().getId();

	}

}
