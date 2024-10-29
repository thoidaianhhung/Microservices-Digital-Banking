package com.vti.accountserver.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "account_type")
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "create_dt")
    @CreationTimestamp
    private LocalDateTime createDt;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "customer_id")
    private Long customerId;

    public enum Status {
        OPENING, ACTIVE, CLOSED
    }

    public enum AccountType {
        SAVINGS_ACCOUNT, LOANS_ACCOUNT
    }
}
