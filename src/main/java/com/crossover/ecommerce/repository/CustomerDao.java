package com.crossover.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.crossover.ecommerce.domain.Customer;

public interface CustomerDao extends Repository<Customer, Integer> {

	List<Customer> findAll();

	Customer findOne(Integer id);

	Customer findByEmail(String email);

	Customer save(Customer customer);

}
