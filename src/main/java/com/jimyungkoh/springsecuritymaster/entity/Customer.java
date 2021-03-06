package com.jimyungkoh.springsecuritymaster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private final String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonIgnore
    private final String pwd;

    private final String role;

    @Column(name = "create_dt")
    private String createDt;

    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "customer")
    private Set<Authority> authorities;

    public Customer(String name, String email, String mobileNumber, String pwd, String role, String createDt) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.pwd = pwd;
        this.role = role;
        this.createDt = createDt;
    }
}
