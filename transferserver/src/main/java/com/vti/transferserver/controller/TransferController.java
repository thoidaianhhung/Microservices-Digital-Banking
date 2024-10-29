package com.vti.transferserver.controller;

import com.vti.transferserver.dto.TransferDto;
import com.vti.transferserver.form.TransferCreateForm;
import com.vti.transferserver.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TransferController {

    private final TransferService transferService;

    /**
     * Handles the fund transfer request.
     *
     * @param transferCreateForm The fund transfer request object.
     * @return The response entity containing the fund transfer response.
     */
    @PostMapping("/transfers")
    TransferDto createTransfer(@RequestBody TransferCreateForm transferCreateForm) {
        return transferService.createTransfer(transferCreateForm);
    }

    /**
     * Retrieves the transfer details from the given reference ID.
     *
     * @param referenceId the reference ID of the transfer
     * @return the transfer details as a ResponseEntity
     */
    @GetMapping("/transfers/{referenceId}")
    public TransferDto getTransferDetailsFromReferenceId(@PathVariable("referenceId") String referenceId) {
        return transferService.getTransferDetailsFromReferenceId(referenceId);
    }

    /**
     * Retrieves all fund transfers by account ID.
     *
     * @param accountNumber the Account Number of the account
     * @return the list of fund transfer DTOs
     */
    @GetMapping("/accounts/{accountNumber}/transfers")
    Page<TransferDto> findTransferByAccountNumber(@PathVariable("accountNumber") String accountNumber, Pageable pageable) {
        return transferService.findTransferByAccountNumber(accountNumber, pageable);
    }
}
