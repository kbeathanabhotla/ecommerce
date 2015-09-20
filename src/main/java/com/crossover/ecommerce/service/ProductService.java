package com.crossover.ecommerce.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.ecommerce.domain.Product;
import com.crossover.ecommerce.repository.ProductDao;

@Service
@Transactional
public class ProductService {

	@Resource
	private ProductDao productDao;

	public List<Product> getByCategoryId(Byte id) {
		return productDao.findByCategoryId(id);
	}

	public Product getById(Integer id) {
		return productDao.findOne(id);
	}

}
