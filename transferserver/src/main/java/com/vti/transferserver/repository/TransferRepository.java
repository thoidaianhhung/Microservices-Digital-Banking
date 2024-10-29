package com.vti.transferserver.repository;

import com.vti.transferserver.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    /**
     * Finds a transfer by transaction reference.
     *
     * @param referenceId the transaction reference ID
     * @return an optional fund transfer object
     */
    Optional<Transfer> findTransferByReferenceId(String referenceId);

    /**
     * Retrieves a Page of Transfer objects based on the provided from account ID.
     *
     * @param accountNumber The Account number of the  account.
     * @return A Page of Transfer objects.
     */
    Page<Transfer> findTransferByFromAccount(String accountNumber, Pageable pageable);
}
