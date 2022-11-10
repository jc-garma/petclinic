package org.springframework.samples.petclinic.bill.dto;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.dto.Visit;


@Entity
@Table(name = "bills")
public class Bill extends BaseEntity {

	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paymentDate;

	@Column(name = "money")
	@Digits(integer=5, fraction=2)
	@DecimalMin("0.0")
	private Double money;

	//@OneToOne(mappedBy="bill")
	//private Visit visit;

	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinColumn(name = "bill_id")
	//@OrderBy("visit_date ASC")
	//private Set<Visit> visits = new LinkedHashSet<>();

	public Date getPaymentDate() { return this.paymentDate; }

	public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

	public Double getMoney() { return this.money; }

	public void setMoney(Double money) { this.money = money; }

	/*public Collection<Visit> getVisits() {
		return this.visits;
	}

	public void addVisit(Visit visit) {
		getVisits().add(visit);
	}*/

	/*public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}*/
}
