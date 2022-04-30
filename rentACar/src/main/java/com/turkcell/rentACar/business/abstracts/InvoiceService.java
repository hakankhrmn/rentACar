package com.turkcell.rentACar.business.abstracts;

import com.turkcell.rentACar.business.dtos.invoiceDtos.GetInvoiceDto;
import com.turkcell.rentACar.business.dtos.invoiceDtos.InvoiceListDto;
import com.turkcell.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

import java.util.List;

public interface InvoiceService {
    DataResult<List<InvoiceListDto>> getAll();
    DataResult<GetInvoiceDto> add(CreateInvoiceRequest createIndividualCustomerRequest);
    Result delete(int id);

    DataResult<GetInvoiceDto> getById(int id);
    void checkIfInvoiceExists(int id);
}
