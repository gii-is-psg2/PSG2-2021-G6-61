package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.PropuestaAdopcionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdoptionController {
	
	@Autowired
    private PetService petService;
	@Autowired
    private UserService userService;
	@Autowired
    private OwnerService ownerService;
	@Autowired
	private PropuestaAdopcionService propuestaAdopcionService;

	
    @GetMapping(value = { "/adoptions" })
    public String showAdoptions(final Map<String, Object> model,@RequestParam(value = "message", required = false) final String message, 
    		Authentication auth, HttpServletRequest request) {


    	Principal principal = request.getUserPrincipal();
    	String username =  principal.getName(); 
    	User  user = userService.findUser(username);
    	Owner owner = ownerService.findOwnerByUser(user);
        final List<Pet> pets = this.petService.findByEnAdopcionTrueAndOwnerNotLike(owner);
        
        model.put("pets", pets);
		model.put("message", message);
        return "adoptions/adoptions";
    }

    
    @GetMapping(value = "/adoptions/{petId}/enAdopcion")
  	public String adoptionsUpdateForm(@PathVariable("petId") final int petId, 
  			final ModelMap model, final RedirectAttributes redirectAttributes) {                                               
        
    	Pet pet = petService.findPetById(petId);
    	if(pet != null) {
    		
    		pet.setEnAdopcion(true);
    		petService.save(pet);
    		redirectAttributes.addAttribute("message", "AdoptionProposalSuccessful");

    		return "redirect:/owners/" + pet.getOwner().getId();
    	}else {
    	
    	return "redirect:/owners";
    	}
  	}
    
    
    @GetMapping(value = "/adoptions/{petId}/noEnAdopcion")
  	public String adoptionsCancelForm(@PathVariable("petId") final int petId, 
  			final ModelMap model, final RedirectAttributes redirectAttributes) {                                               
        
    	Pet pet = petService.findPetById(petId);
    	if(pet != null) {
    		this.propuestaAdopcionService.deleteByPet(pet);
    		pet.setEnAdopcion(false);
    		petService.save(pet);
    		redirectAttributes.addAttribute("message", "AdoptionCancelSuccessful");
    		return "redirect:/owners/" + pet.getOwner().getId();
    	}else {
    	
    	return "redirect:/owners";
    	}
  	}
  
  	
}
