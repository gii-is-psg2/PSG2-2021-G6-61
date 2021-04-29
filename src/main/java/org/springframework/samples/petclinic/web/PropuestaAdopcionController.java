package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.BookValidator;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PropuestaAdopcion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.BookService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.PropuestaAdopcionService;
import org.springframework.samples.petclinic.service.RoomService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PropuestaAdopcionController {
	
	private static final String VIEWS_PROPUESTA_CREATE_OR_UPDATE_FORM = "adoptions/adoptionProposalForm";
	
	
	private final PropuestaAdopcionService propuestaAdopcionService; 
	private final PetService petService;
	private final UserService userService;
	private final OwnerService ownerService;

	@Autowired
	public PropuestaAdopcionController(PropuestaAdopcionService propuestaAdopcionService, PetService petService, 
									UserService userService, OwnerService ownerService) {
		this.propuestaAdopcionService = propuestaAdopcionService;
		this.petService = petService;
		this.userService = userService;
		this.ownerService = ownerService;
	}
	
	@GetMapping(value = "/proposals/{petId}/new")
	public String initCreationForm(@PathVariable("petId") final int petId, final ModelMap model) {
		final PropuestaAdopcion propuesta = new PropuestaAdopcion();
		Pet pet = this.petService.findPetById(petId);
		if(pet.getEnAdopcion().equals(false)) {
			return "redirect:/adoptions/";
		}else {
			propuesta.setPet(pet);
			model.put("propuestaAdopcion", propuesta);
			return VIEWS_PROPUESTA_CREATE_OR_UPDATE_FORM;
		}
	}
	
	@PostMapping(value = "/proposals/{petId}/new")
	public String processCreationForm(@Valid PropuestaAdopcion propuestaAdopcion, final BindingResult result, @PathVariable("petId") final int petId,
									final ModelMap model, HttpServletRequest request,  final RedirectAttributes redirectAttributes) {
		
		Pet pet = this.petService.findPetById(petId);
		propuestaAdopcion.setPet(pet);
		
		if (result.hasErrors()) {
			return VIEWS_PROPUESTA_CREATE_OR_UPDATE_FORM;
		}
		else {
			String username = request.getUserPrincipal().getName();
	    	User  user = userService.findUser(username);
	    	Owner owner = ownerService.findOwnerByUser(user); 
	    	PropuestaAdopcion proposal = this.propuestaAdopcionService.findPropuestaOwnerPet(owner, pet);
	    	
	    	if(proposal != null) {
	    		proposal.setDescription(propuestaAdopcion.getDescription());
	    		this.propuestaAdopcionService.save(proposal);
	    	}else {
	    		propuestaAdopcion.setOwner(owner);
	    		this.propuestaAdopcionService.save(propuestaAdopcion);
	    	}
	    	
	    	redirectAttributes.addAttribute("message", "ProposalSuccessful");
			
           return "redirect:/adoptions/";
		}
	}
	
	@GetMapping(value = "/proposals/{petId}/list")
	public String listProposals(final Map<String, Object> model, @PathVariable("petId") final int petId) {
		Pet pet = this.petService.findPetById(petId);
		final List<PropuestaAdopcion> propuestas = this.propuestaAdopcionService.findByPet(pet);
		model.put("pet",pet);
		model.put("propuestas",propuestas);
		return "adoptions/proposalsList";
	}
	
	@GetMapping(value = "/proposals/{proposalId}/accept")
	public String acceptProposal(final Map<String, Object> model, @PathVariable("proposalId") final int proposalId,
								final RedirectAttributes redirectAttributes) {
		
		Optional<PropuestaAdopcion> propuestaOpt = this.propuestaAdopcionService.findById(proposalId);
		
		if(propuestaOpt.isPresent()) {
			PropuestaAdopcion propuesta = propuestaOpt.get();
		
			Pet pet = propuesta.getPet();
			Owner oldOwner = pet.getOwner();
			Owner newOwner = propuesta.getOwner();
	
			oldOwner.removePet(pet);
			pet.setEnAdopcion(false);
			newOwner.addPet(pet);
			
			this.propuestaAdopcionService.deleteByPet(pet);
			
			this.ownerService.save(oldOwner);
			this.ownerService.save(newOwner);
			this.petService.save(pet);
	
			redirectAttributes.addAttribute("message", "ProposalAccepted");
			
			return "redirect:/owners/" + oldOwner.getId();
			
		}else {
			return "redirect:/owners";
		}
	}
}
