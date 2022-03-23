package com.turkcell.rentACar.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ordered_additional_services")
public class OrderedAdditionalService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ordered_additional_service_id")
	private int orederedAdditionalServiceId;
	
	@ManyToOne
	private AdditionalService additionalService;
	
	@ManyToOne
	private CarRent carRent;
}
