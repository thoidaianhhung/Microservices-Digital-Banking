package com.vti.transferserver.service;


import com.vti.transferserver.dto.TransferDto;
import com.vti.transferserver.form.TransferCreateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferService {

    /**
     * Transfers funds from one account to another.
     *
     * @param transferCreateForm The request object containing the details of the fund transfer.
     * @return The response object containing the result of the fund transfer.
     */
    TransferDto createTransfer(TransferCreateForm transferCreateForm);

    /**
     * Retrieve transfer details based on the provided reference ID.
     *
     * @param referenceId The reference ID of the transfer.
     * @return The transfer details as a FundTransferDto object.
     */
    TransferDto getTransferDetailsFromReferenceId(String referenceId);

    /**
     * Retrieves all fund transfers associated with the given account ID.
     *
     * @param accountNumber the Account number of the account
     * @return a list of FundTransferDto objects representing the fund transfers
     */
    Page<TransferDto> findTransferByAccountNumber(String accountNumber, Pageable pageable);
}
