package org.springframework.samples.petclinic.visit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.bill.dao.BillRepository;
import org.springframework.samples.petclinic.owner.dao.OwnerRepository;
import org.springframework.samples.petclinic.owner.dto.Owner;
import org.springframework.samples.petclinic.pet.dto.Pet;
import org.springframework.samples.petclinic.visit.dao.VisitRepository;
import org.springframework.samples.petclinic.visit.dto.Visit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class VisitService {

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private VisitRepository visitRepository;

	@Autowired
	private BillRepository billRepository;

	public Visit findPetWithVisit(int ownerId, int petId, Map<String, Object> model) {
		Owner owner = this.ownerRepository.findById(ownerId);

		Pet pet = owner.getPet(petId);
		model.put("pet", pet);
		model.put("owner", owner);

		Visit visit = new Visit();
		pet.addVisit(visit);
		return visit;
	}

	public Integer addVisitToPet(Owner owner, Integer petId, Visit v) {
		owner.addVisit(petId, v);
		this.ownerRepository.save(owner);
		return owner.getId();
	}

	public List<Visit> findVisits(String f) {
		List<Visit> visitsResult = new ArrayList<>();
		switch (f.trim().toLowerCase(Locale.ROOT)) {
			case "pagadas":
				for (Visit v : visitRepository.findAll()) {
					if ((v.getBill() != null) && (v.getBill().getPayment_date() != null)) {
						visitsResult.add(v);
					}
				}
				break;
			case "no_pagadas":
				for (Visit v : visitRepository.findAll()) {
					if ((v.getBill() != null) && (v.getBill().getPayment_date() == null)) {
						visitsResult.add(v);
					}
				}
		}
		return visitsResult;
	}

}
