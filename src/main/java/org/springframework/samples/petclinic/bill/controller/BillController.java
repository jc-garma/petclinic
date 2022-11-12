package org.springframework.samples.petclinic.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.samples.petclinic.bill.service.BillService;
import org.springframework.samples.petclinic.visit.dto.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BillController {

	@Autowired
	private BillService billService;

	/*public BillController(BillService billService) { this.billService = billService; }*/

	@RequestMapping(value = "/bills", method = RequestMethod.GET)
	@ResponseBody
	public List<Bill> findBills(@RequestParam(name = "filter", defaultValue = "pagadas", required = true) String f) {
		return billService.findBills(f);
	}

	@GetMapping("/bills/{idBill}/visit/{idVisit}")
	@ResponseBody
	public Visit showVisitDetails(@PathVariable("idBill") Integer billId, @PathVariable("idVisit") Integer visitId) {
		return billService.showVisitDetails(billId, visitId);
	}

	// Spring MVC llama al método loadPetWithVisit(...) antes de llamar a processNewVisitForm
	@PostMapping("/bills/{idBill}/visit/{idVisit}")
	@ResponseBody
	public Visit updateVisitDetails(@PathVariable("idBill") Integer billId, @PathVariable("idVisit") Integer visitId) {
		return billService.updateVisitDetails(billId, visitId);
	}

}
