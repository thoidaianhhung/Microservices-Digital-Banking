package com.vti.transactionserver.mapper;

import com.vti.transactionserver.dto.TransactionDto;
import com.vti.transactionserver.entity.Transaction;
import com.vti.transactionserver.form.TransactionCreateForm;
import com.vti.transactionserver.form.TransactionUpdateForm;

public class TransactionMapper {
    private TransactionMapper() {

    }

    public static Transaction map(TransactionCreateForm transactionCreateForm) {
        var transaction = new Transaction();
        transaction.setReferenceId(transactionCreateForm.getReferenceId());
        transaction.setAccountNumber(transactionCreateForm.getAccountNumber());
        transaction.setTransactionType(Transaction.TransactionType.valueOf(transactionCreateForm.getTransactionType()));
        transaction.setAmount(transactionCreateForm.getAmount());
        transaction.setStatus(Transaction.TransactionStatus.valueOf(transactionCreateForm.getStatus()));
        transaction.setComments(transactionCreateForm.getComments());
        return transaction;
    }

    public static TransactionDto map(Transaction transaction) {
        var dto = new TransactionDto();
        dto.setReferenceId(transaction.getReferenceId());
        dto.setAccountNumber(transaction.getAccountNumber());
        dto.setTransactionType(transaction.getTransactionType().name());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setStatus(transaction.getStatus());
        dto.setComments(transaction.getComments());
        return dto;
    }

    public static void map(TransactionUpdateForm transactionUpdateForm, Transaction transaction) {
        transaction.setReferenceId(transactionUpdateForm.getReferenceId());
        transaction.setAccountNumber(transactionUpdateForm.getAccountNumber());
        transaction.setTransactionType(Transaction.TransactionType.valueOf(transactionUpdateForm.getTransactionType()));
        transaction.setAmount(transactionUpdateForm.getAmount());
        transaction.setStatus(Transaction.TransactionStatus.valueOf(transactionUpdateForm.getStatus()));
        transaction.setComments(transactionUpdateForm.getComments());
    }
}
