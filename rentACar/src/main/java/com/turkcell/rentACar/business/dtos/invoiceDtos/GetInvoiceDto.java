package com.turkcell.rentACar.business.dtos.invoiceDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceDto {
	
	private int invoiceId;
	
	private String invoiceNumber;
	
	private LocalDate invoiceDate;

	private double totalPayment;
	
	//customer
	private int customerId;
	
	//car rent
	private int carRentId;

	private String description;
	
	private String rentCityName;

	private String returnCityName;

	private LocalDate rentDate;

	private LocalDate returnDate;
}
