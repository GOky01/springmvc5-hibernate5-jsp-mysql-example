package com.project.springmvc.repository;

import com.project.springmvc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
