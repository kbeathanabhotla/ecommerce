package com.crossover.ecommerce.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.ecommerce.domain.CustomerOrder;
import com.crossover.ecommerce.domain.OrderedProduct;
import com.crossover.ecommerce.domain.OrderedProductId;
import com.crossover.ecommerce.domain.Product;
import com.crossover.ecommerce.repository.OrderedProductDao;

@Service
@Transactional
public class OrderedProductService {

	@Resource
	private OrderedProductDao orderedProductDao;

	public void save(CustomerOrder order, Map<Product, Integer> items) {
		Set<Product> keys = items.keySet();
		Set<OrderedProduct> set = new HashSet<OrderedProduct>(0);
		for (Product key : keys) {
			int productId = key.getId();
			int orderId = order.getId();
			OrderedProductId orderedProductId = new OrderedProductId(orderId, productId);
			OrderedProduct orderedProduct = new OrderedProduct(orderedProductId, key, order, items.get(key).shortValue());
			set.add(orderedProduct);
		}
		orderedProductDao.save(set);
	}

}
