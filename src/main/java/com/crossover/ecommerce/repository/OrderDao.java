package com.crossover.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.crossover.ecommerce.domain.CustomerOrder;

public interface OrderDao extends Repository<CustomerOrder, Integer> {

	List<CustomerOrder> findAll();

	CustomerOrder save(CustomerOrder order);

}
