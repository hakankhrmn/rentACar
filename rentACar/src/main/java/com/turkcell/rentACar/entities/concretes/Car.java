package com.turkcell.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="car_id")
	private int carId;
	
	@Column(name="daily_price")
	private double dailyPrice;
	
	@Column(name="model_year")
	private int modelYear;

	@Column(name = "kilometer_information")
	private double kilometerInformation;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="color_id")
	private Color color;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CarMaintenance> carMaintenances;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CarRent> carRents;

	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CarDamage> carDamages;
}
