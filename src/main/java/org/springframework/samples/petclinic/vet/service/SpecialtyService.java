package org.springframework.samples.petclinic.vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.specialty.dao.SpecialtyRepository;
import org.springframework.samples.petclinic.specialty.dto.Specialty;
import org.springframework.samples.petclinic.vet.dto.Vet;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SpecialtyService {
	@Autowired
	private SpecialtyRepository specialtyRepository;

	public SpecialtyService(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}

	public void save(Specialty specialty){
		this.specialtyRepository.save(specialty);
	}

	public Specialty findById(Integer id){
		return this.specialtyRepository.findById(id);
	}

	public Collection<Vet> findAll(){
		return this.specialtyRepository.findAll();
	}

	public Page<Vet> findAll(Pageable pageable){
		return this.specialtyRepository.findAll(pageable);
	}

}
