package org.springframework.samples.petclinic.owner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.dao.OwnerRepository;
import org.springframework.samples.petclinic.owner.dto.Owner;
import org.springframework.samples.petclinic.owner.dto.PetType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;

	public OwnerService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	public void save(Owner owner){
		this.ownerRepository.save(owner);
	}

	public Owner findById(Integer id){
		return this.ownerRepository.findById(id);
	}

	public List<PetType> findPetTypes(){
		return this.ownerRepository.findPetTypes();
	}

	public Page<Owner> findByLastName(String lastName, Pageable pageable){
		return this.ownerRepository.findByLastName(lastName, pageable);
	}

	public Page<Owner> findAll(Pageable pageable){
		return this.ownerRepository.findAll(pageable);
	}

}
