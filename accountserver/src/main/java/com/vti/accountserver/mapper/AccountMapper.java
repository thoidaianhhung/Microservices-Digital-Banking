package com.vti.accountserver.mapper;


import com.vti.accountserver.dto.AccountDto;
import com.vti.accountserver.entity.Account;
import com.vti.accountserver.form.AccountCreateForm;
import com.vti.accountserver.form.AccountUpdateForm;

public class AccountMapper {
    private AccountMapper() {

    }

    public static Account map(AccountCreateForm accountCreateForm) {
        var account = new Account();
        account.setAccountNumber(accountCreateForm.getAccountNumber());
        account.setAccountType(Account.AccountType.valueOf(accountCreateForm.getAccountType()));
        account.setBranchAddress(accountCreateForm.getBranchAddress());
        account.setStatus(Account.Status.valueOf(accountCreateForm.getStatus()));
        return account;
    }

    public static AccountDto map(Account account) {
        var dto = new AccountDto();
        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType());
        dto.setBranchAddress(account.getBranchAddress());
        dto.setStatus(account.getStatus());
        dto.setCreateDt(account.getCreateDt());
        dto.setBalance(account.getBalance());
        return dto;
    }

    public static void map(AccountUpdateForm accountUpdateForm, Account account) {
        account.setAccountNumber(accountUpdateForm.getAccountNumber());
        account.setAccountType(Account.AccountType.valueOf(accountUpdateForm.getAccountType()));
        account.setBranchAddress(accountUpdateForm.getBranchAddress());
        account.setStatus(Account.Status.valueOf(accountUpdateForm.getStatus()));
        account.setBalance(accountUpdateForm.getBalance());
    }
}
