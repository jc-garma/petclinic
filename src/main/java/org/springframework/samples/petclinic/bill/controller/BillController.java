package org.springframework.samples.petclinic.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.samples.petclinic.bill.service.BillService;
import org.springframework.samples.petclinic.owner.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
public class BillController {

	@Autowired
	private final BillService billService;

	public BillController(BillService billService) { this.billService = billService; }


	@RequestMapping(value = "/bills", method = RequestMethod.GET)
	public ResponseEntity<Bill> list() {
		List<Bill> bills = billService.list();
		return new ResponseEntity(bills, HttpStatus.OK);
	}

	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	public ResponseEntity<Bill> userById(@RequestParam(value = "id") int id) {
		Bill bill = billService.findById(id);
		return new ResponseEntity(bill, HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Bill> create(@Valid @RequestBody Bill bill) {
		Bill billCreated = billService.create(bill);
		return new ResponseEntity(billCreated, HttpStatus.CREATED);
	}



}
