package com.turkcell.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="invoice_id")
	private int invoiceId;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	@Column(name="invoice_date")
	private LocalDate invoiceDate;

	@Column(name="total_payment")
	private double totalPayment;

	@Column(name="total_rent_day")
	private int totalRentDay;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "car_rent_id")
	private CarRent carRent;

	@OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Payment payment;
}
