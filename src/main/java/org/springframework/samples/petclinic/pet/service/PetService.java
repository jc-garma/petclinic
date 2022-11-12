package org.springframework.samples.petclinic.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.dao.OwnerRepository;
import org.springframework.samples.petclinic.owner.dto.Owner;
import org.springframework.samples.petclinic.pet.dto.Pet;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

@Service
public class PetService {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	@Autowired
	private OwnerRepository ownerRepository;

	public Pet getPet(Integer ownerId, Integer petId) {
		return petId == null ? new Pet() : this.ownerRepository.findById(ownerId).getPet(petId);
	}

	public Pet createNewPet() { return new Pet(); }

	public Pet setPetToOwner(Owner o) {
		Pet pet = new Pet();
		o.addPet(pet);
		return pet;
	}

	public String findPetsForForm(Owner o, Pet p, BindingResult r, ModelMap m) {
		if (StringUtils.hasLength(p.getName()) && p.isNew() && o.getPet(p.getName(), true) != null) {
			r.rejectValue("name", "duplicate", "already exists");
		}

		o.addPet(p);
		if (r.hasErrors()) {
			m.put("pet", p);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		this.ownerRepository.save(o);
		return "redirect:/owners/{ownerId}";
	}

	public Pet getOwnerPet(Owner o, Integer petId) {
		return o.getPet(petId);
	}

	public Integer addPetToOwner(Owner owner, Pet pet) {
		owner.addPet(pet);
		this.ownerRepository.save(owner);
		return owner.getId();
	}

}
