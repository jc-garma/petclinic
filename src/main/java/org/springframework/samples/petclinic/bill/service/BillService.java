package org.springframework.samples.petclinic.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.bill.dao.BillRepository;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.samples.petclinic.visit.dao.VisitRepository;
import org.springframework.samples.petclinic.visit.dto.Visit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private VisitRepository visitRepository;

	public List<Bill> findBills(String filter) {
		List<Bill> billsResult = new ArrayList<>();

		switch (filter.trim().toLowerCase(Locale.ROOT)) {
			case "pagadas":
				billsResult = billRepository.getPaidBills();
				break;
			case "no_pagadas":
				billsResult = billRepository.getUnpaidBills();
				break;
		}
		return billsResult;
	}

	public Visit showVisitDetails(Integer billId, Integer visitId) {
		Bill b = billRepository.findById(billId);
		Visit v = visitRepository.findById(visitId);
		if (b.equals(v.getBill())) {
			return v;
		}
		return null;
	}

	public Visit updateVisitDetails(Integer billId, Integer visitId) {
		Bill b = billRepository.findById(billId);
		Visit v = visitRepository.findById(visitId);
		if (!(b.getId().equals(v.getBill().getId()))) {
			v.setBill(b);
			visitRepository.save(v);
			v = visitRepository.findById(visitId);
		}
		return v;
	}

}
