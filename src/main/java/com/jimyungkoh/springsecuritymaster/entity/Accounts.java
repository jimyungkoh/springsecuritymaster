package com.jimyungkoh.springsecuritymaster.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Accounts {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private String createDt;
}
