package org.springframework.samples.petclinic.bill.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.visit.dto.Visit;


@Entity
@Table(name = "bills")
public class Bill extends BaseEntity {

	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate payment_date;

	@NotEmpty
	@Column(name = "money")
	@Digits(integer=10, fraction=2)
	@DecimalMin("0.00")
	private Double money;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "visit_id")
	private Visit visit;

	public LocalDate getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
