package com.shiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.shiva.entity.Contact;
@Component
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
