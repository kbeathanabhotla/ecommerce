package com.crossover.ecommerce.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.crossover.ecommerce.domain.CustomerOrder;
import com.crossover.ecommerce.domain.Product;
import com.crossover.ecommerce.repository.OrderedProductDao;

@RunWith(MockitoJUnitRunner.class)
public class OrderedProductServiceTests {

	/** {@link OrderedProductService} instance under test */
	@InjectMocks
	private OrderedProductService service;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private OrderedProductDao mockOrderedProductDao;
	
	/**
	 * Method to perform setup work for each test.
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Method to perform tear down work for each test.
	 */
	@After
	public void tearDown() throws Exception {
		Mockito.reset(mockOrderedProductDao);
	}

	/**
	 * Test {@link OrderedProductService#save(CustomerOrder, Map< Product , Integer>)}
	 */
	@Test
	public void save() {
		// given
		CustomerOrder order = new CustomerOrder();
		Map<Product, Integer> items = new HashMap<Product, Integer>();
		// when
		service.save(order, items);
		// then
		Mockito.verify(mockOrderedProductDao).save(Mockito.anySet());
	}

}
