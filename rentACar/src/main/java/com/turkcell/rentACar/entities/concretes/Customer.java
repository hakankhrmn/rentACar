package com.turkcell.rentACar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "user_id")
public class Customer extends User{

	@Column(name="customer_id", insertable = false, updatable = false)
	private int customerId;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Invoice> invoices;
	
}
