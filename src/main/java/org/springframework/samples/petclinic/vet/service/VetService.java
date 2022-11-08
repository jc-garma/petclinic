package org.springframework.samples.petclinic.vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.vet.dto.Vet;
import org.springframework.samples.petclinic.vet.dao.VetRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class VetService {
	@Autowired
	private VetRepository vetRepository;

	public VetService(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	public void save(Vet vet){
		this.vetRepository.save(vet);
	}

	public Vet findById(Integer id){
		return this.vetRepository.findById(id);
	}

	public List<Vet> findBySpecialityName(String name){
		return this.findBySpecialityName(name);
	}

	public List<Vet> findByLastName(String lastName){
		return this.findByLastName(lastName);
	}

	public List<Vet> findByFirstNameAndLastName(String firstName, String lastName){
		return this.findByFirstNameAndLastName(firstName, lastName);
	}

	public List<Vet> findByFirstNameOrLastName(String firstName, String lastName) {
		return this.findByFirstNameOrLastName(firstName, lastName);
	}

	public Collection<Vet> findAll(){
		return this.vetRepository.findAll();
	}

	public Page<Vet> findAll(Pageable pageable){
		return this.vetRepository.findAll(pageable);
	}

}
