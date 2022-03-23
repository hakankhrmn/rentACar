package com.turkcell.rentACar.business.dtos.paymentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentDto {

    private int paymentId;

    private LocalDate paymentDate;

    private int invoiceId;
}
