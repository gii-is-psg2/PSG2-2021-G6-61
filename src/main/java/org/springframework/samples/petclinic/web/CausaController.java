/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.service.CausaService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class CausaController {
	
	private static final String MESSAGE_CODE = "message";

	private final CausaService causaService;
	private final DonationService donationService;

	@Autowired
	public CausaController(final CausaService casuaService, final DonationService donationService) {
		this.causaService = casuaService;
		this.donationService = donationService;
	}

	@ModelAttribute("causas")
	public Collection<Integer> populateCausas() {
		return this.causaService.findAll().stream().map(Causa::getId).collect(Collectors.toList());
	}

	@ModelAttribute("causa")
	public Causa populateCausa() {
		return new Causa();
	}

	@GetMapping(value = { "/causas" })
	public String showCausasList(final Map<String, Object> model,
			@RequestParam(value = MESSAGE_CODE, required = false) final String message,
			@RequestParam(value = "message2", required = false) final String message2) {
		final List<Causa> causas = this.causaService.findAll();
		model.put("causas", causas);
		model.put(MESSAGE_CODE, message);
		model.put("message2", message2);
		return "causes/causasList";
	}

	@GetMapping(value = "/causas/{causaId}")
	public String showCausasDetail(@PathVariable("causaId") final int causaId, final ModelMap model,
			@RequestParam(value = MESSAGE_CODE, required = false) final String message,
			@RequestParam(value = "message2", required = false) final String message2) {
		final Optional<Causa> causa = this.causaService.findById(causaId);

		if (causa.isPresent()) {
			model.put("causa", causa.get());
			model.put("donaciones", this.donationService.findByCausa(causaId));
			model.put(MESSAGE_CODE, message);
			model.put("message2", message2);
			return "causes/causaDetail";
		}
		return "redirect:/causas";
	}

	@GetMapping(value = "/causas/new")
	public String initCreationForm(final Map<String, Object> model) {
		final Causa causa = new Causa();
		model.put("causa", causa);
		return "causes/createCausaForm";
	}

	@PostMapping(value = "/causas/new")
	public String processCreationForm(@Valid final Causa causa, final BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "causes/createCausaForm";
		} else {
			// creating owner, user and authorities
			causa.setFinalizada(false);
			causa.setAcumulado(0.0);
			this.causaService.save(causa);
			redirectAttributes.addAttribute(MESSAGE_CODE, "CausaSavedSuccessful");
			return "redirect:/causas";
		}
	}
}
