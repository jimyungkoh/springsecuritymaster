package com.jimyungkoh.springsecuritymaster.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "account_transactions")
@Getter
@Setter
public class AccountTransactions {
    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_number")
    private Accounts accounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name="transaction_dt")
    private Date transactionDt;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name = "transaction_amt")
    private int transactionAmt;

    @Column(name = "closing_balance")
    private int closingBalance;

    @Column(name = "create_dt")
    private String createDt;
}
