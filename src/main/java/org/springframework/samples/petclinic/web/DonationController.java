package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.CausaService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String newDonation(@Valid Donation donation, BindingResult result, final ModelMap model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "causes/newDonation";
		}else {
			Causa causa = (Causa)model.getAttribute("causa");
			
			double scale = Math.pow(10, 2);
		    donation.setCantidad(Math.round(donation.getCantidad() * scale) / scale);
			Double nuevoAcum= donation.getCantidad()+causa.getAcumulado();
			
			if(nuevoAcum>causa.getObjetivoPresupuestario()) {
				donation.setCantidad(causa.getObjetivoPresupuestario()-causa.getAcumulado());
				redirectAttributes.addAttribute("message", "TargetAchieved");
				causa.setFinalizada(true);
				nuevoAcum= causa.getObjetivoPresupuestario();
			}else redirectAttributes.addAttribute("message", "DonationSubmitted");
			
			causa.setAcumulado(nuevoAcum);
			donation.setCausa(causa);
			donation.setDonante((String)model.getAttribute("donor"));
			donation.setFecha(LocalDate.now());
			donationService.save(donation);
			return "redirect:/causas/{causaId}";
		}
	}
}
