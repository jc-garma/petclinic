package org.springframework.samples.petclinic.bill.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.samples.petclinic.vet.dto.Vet;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface BillRepository extends Repository<Bill, Integer> {

	//@Transactional(readOnly = true)
	//@Cacheable("bills")
	List<Bill> findAll() throws DataAccessException;

	//@Query("SELECT bill FROM Bill bill left join fetch bill.visits WHERE bill.id =:id")
	//@Transactional(readOnly = true)
	//Bill findByIdWithVisit(@Param("id") Integer id);

	/*SELECT bill FROM bills where bill.id = id*/
	Bill findById(@Param("id") Integer id);

	Bill save (Bill bill);

	@Query("SELECT bill FROM Bill bill")
	@Transactional(readOnly = true)
	Page<Bill> findAll(Pageable pageable);

}
