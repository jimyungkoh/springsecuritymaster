package com.jimyungkoh.springsecuritymaster.repository;

import com.jimyungkoh.springsecuritymaster.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
