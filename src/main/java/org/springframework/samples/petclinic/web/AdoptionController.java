package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdoptionController {
	
	@Autowired
    private PetService petService;
	
    @GetMapping(value = { "/adoptions" })
    public String showAdoptions(final Map<String, Object> model,@RequestParam(value = "message", required = false) final String message) {
        final List<Pet> pets = this.petService.findByEnAdopcionTrueAndOwnerNotLike(null);
        //sacar el id del ownwer que está registrado en este momento 
        model.put("pets", pets);
		model.put("message", message);
        return "adoptions/adoptionsList";
    }
    
    @PostMapping(value = "/adoptions/{petId}/edit")
  	public String adoptionsUpdateForm(@Valid final Pet pet, final BindingResult result, final Owner owner,
  			@PathVariable("petId") final int petId, final ModelMap model, final RedirectAttributes redirectAttributes) {                                               
               try { 
                   pet.setEnAdopcion(true);
                   this.petService.savePet(pet); 
                   redirectAttributes.addAttribute("message", "AdoptionSavedSuccessful");

                   } catch (final Exception ex) {
                    return "redirect:/owners/{ownerId}";
                   }
  			return "redirect:/owners";
  		}
    
    @PostMapping(value = "/adoptions/{petId}/delete")
   	public String adoptionsDeleteForm(@Valid final Pet pet, final BindingResult result, final Owner owner,
   			@PathVariable("petId") final int petId, final ModelMap model,final RedirectAttributes redirectAttributes) {                                               
                try { 
                    pet.setEnAdopcion(false);
                    this.petService.savePet(pet);
                    redirectAttributes.addAttribute("message", "AdoptionDeletedSuccessful");

                    } catch (final Exception ex) {
                     return "redirect:/owners/{ownerId}";
                    }
   			return "redirect:/owners";
   		}
  	
}
