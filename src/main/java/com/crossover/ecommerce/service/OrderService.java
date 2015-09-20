package com.crossover.ecommerce.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.ecommerce.domain.Customer;
import com.crossover.ecommerce.domain.CustomerOrder;
import com.crossover.ecommerce.repository.CustomerDao;
import com.crossover.ecommerce.repository.OrderDao;

@Service
@Transactional
public class OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private CustomerDao customerDao;

	public CustomerOrder save(Integer customerId, double total) {
		Customer customer = customerDao.findOne(customerId);
		BigDecimal amount = new BigDecimal(total);
		Date dateProcessed = new Date();
		Random random = new Random();
		int refNum = random.nextInt(999999999);

		CustomerOrder order = new CustomerOrder(customer, amount, dateProcessed, refNum);

		return orderDao.save(order);
	}

	public List<CustomerOrder> getAll() {
		return orderDao.findAll();
	}

}
