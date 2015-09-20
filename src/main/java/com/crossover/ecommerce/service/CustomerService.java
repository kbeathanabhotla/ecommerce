package com.crossover.ecommerce.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.ecommerce.domain.Customer;
import com.crossover.ecommerce.repository.CustomerDao;

@Service
@Transactional
public class CustomerService {

	@Resource
	private CustomerDao customerDao;

	public Integer save(Customer customer) {
		Customer persisted = customerDao.save(customer);
		return persisted.getId();
	}

	public List<Customer> getAll() {
		return customerDao.findAll();
	}

	public Customer getById(Integer id) {
		return customerDao.findOne(id);
	}

	public Customer getByEmail(String email) {
		return customerDao.findByEmail(email);
	}

}
