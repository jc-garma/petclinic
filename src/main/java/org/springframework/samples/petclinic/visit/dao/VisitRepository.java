package org.springframework.samples.petclinic.visit.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.visit.dto.Visit;

import java.util.List;

public interface VisitRepository extends Repository<Visit, Integer> {
	List<Visit> findAll();

	@Query("SELECT v FROM Visit v ORDER BY v.date DESC")
	List<Visit> findAllInDescendingOrder();

	Visit findById(Integer visitId);

	void save(Visit v);

}
