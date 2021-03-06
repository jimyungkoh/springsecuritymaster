package com.jimyungkoh.springsecuritymaster.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Long loanNumber;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "start_dt")
    private Date startDt;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    @Column(name = "create_dt")
    private String createDt;
}

