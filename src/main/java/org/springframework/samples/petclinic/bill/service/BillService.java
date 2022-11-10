package org.springframework.samples.petclinic.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.bill.dao.BillRepository;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;

	public BillService(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public void save(Bill bill){
		this.billRepository.save(bill);
	}

	public Bill findById(Integer id){
		return this.billRepository.findById(id);
	}

	public List<Bill> findAll(){
		return this.billRepository.findAll();
	}
	public List<Bill> list() {
		Iterable<Bill> bills = billRepository.findAll();
		List<Bill> list = new ArrayList<Bill>();
		bills.forEach(list::add);
		return list;
	}

	public Bill create(Bill bill) {
		return billRepository.save(bill);
	}

	public Page<Bill> findAll(Pageable pageable){
		return this.billRepository.findAll(pageable);
	}

}
