package com.jimyungkoh.springsecuritymaster.repository;

import com.jimyungkoh.springsecuritymaster.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(Long customerId);
}
