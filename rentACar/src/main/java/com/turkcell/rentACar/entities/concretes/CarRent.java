package com.turkcell.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.turkcell.rentACar.entities.abstracts.CityEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_rents")
public class CarRent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_rent_id")
	private int carRentId;

	@Column(name = "description")
	private String description;
	
	@Column(name = "rent_city")
	private String rentCity;

	@Column(name = "return_city")
	private String returnCity;

	@Column(name = "rent_date")
	private LocalDate rentDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id")
	private Car car;
	
	@OneToMany(mappedBy = "carRent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderedAdditionalService> orderedAdditionalServices;
	
	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;
}
