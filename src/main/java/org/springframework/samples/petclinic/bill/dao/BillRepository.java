package org.springframework.samples.petclinic.bill.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.bill.dto.Bill;

import java.util.List;

public interface BillRepository extends Repository<Bill, Integer> {
	List<Bill> findAll();

	@Query("SELECT b FROM Bill b WHERE b.payment_date IS NOT NULL")
	List<Bill> getPaidBills();

	@Query("SELECT b FROM Bill b WHERE b.payment_date IS NULL")
	List<Bill> getUnpaidBills();

	Bill findById(Integer id);

	void save (Bill bill);

}
