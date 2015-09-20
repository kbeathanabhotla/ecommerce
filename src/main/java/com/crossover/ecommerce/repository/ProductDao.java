package com.crossover.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.crossover.ecommerce.domain.Product;

public interface ProductDao extends Repository<Product, Integer> {

	List<Product> findByCategoryId(Byte id);

	Product findOne(Integer id);

}
