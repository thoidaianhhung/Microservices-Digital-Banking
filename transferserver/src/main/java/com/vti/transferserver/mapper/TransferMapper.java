package com.vti.transferserver.mapper;


import com.vti.transferserver.dto.TransferDto;
import com.vti.transferserver.entity.Transfer;
import com.vti.transferserver.form.TransferCreateForm;
import com.vti.transferserver.form.TransferUpdateForm;

public class TransferMapper {
    private TransferMapper() {

    }

    public static Transfer map(TransferCreateForm transferCreateForm) {
        var transfer = new Transfer();
        transfer.setFromAccount(transferCreateForm.getFromAccount());
        transfer.setToAccount(transferCreateForm.getToAccount());
        transfer.setAmount(transferCreateForm.getAmount());
        transfer.setStatus(Transfer.TransactionStatus.valueOf(transferCreateForm.getStatus()));
        transfer.setTransferType(Transfer.TransferType.valueOf(transferCreateForm.getTransferType()));
        return transfer;
    }

    public static TransferDto map(Transfer transfer) {
        var dto = new TransferDto();
        dto.setReferenceId(transfer.getReferenceId());
        dto.setFromAccount(transfer.getFromAccount());
        dto.setToAccount(transfer.getToAccount());
        dto.setAmount(transfer.getAmount());
        dto.setStatus(transfer.getStatus());
        dto.setTransferType(transfer.getTransferType());
        dto.setTransferredOn(transfer.getTransferredOn());
        return dto;
    }

    public static void map(TransferUpdateForm transferUpdateForm, Transfer transfer) {
        transfer.setFromAccount(transferUpdateForm.getFromAccount());
        transfer.setToAccount(transferUpdateForm.getToAccount());
        transfer.setAmount(transferUpdateForm.getAmount());
        transfer.setStatus(Transfer.TransactionStatus.valueOf(transferUpdateForm.getStatus()));
        transfer.setTransferType(Transfer.TransferType.valueOf(transferUpdateForm.getTransferType()));
    }
}
