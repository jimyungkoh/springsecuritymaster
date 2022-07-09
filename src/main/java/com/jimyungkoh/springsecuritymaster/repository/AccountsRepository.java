package com.jimyungkoh.springsecuritymaster.repository;

import com.jimyungkoh.springsecuritymaster.entity.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
    Accounts findByCustomerId(Long customerId);
}
