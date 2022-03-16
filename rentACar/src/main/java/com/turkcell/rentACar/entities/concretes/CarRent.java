package com.turkcell.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rent_city_id")
	private City rentCity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "return_city_id")
	private City returnCity;

	@Column(name = "rent_date")
	private LocalDate rentDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	private Car car;
	
	@OneToMany(mappedBy = "carRent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderedAdditionalService> orderedAdditionalServices;
	
	@OneToOne(mappedBy = "carRent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Invoice invoice;
}
