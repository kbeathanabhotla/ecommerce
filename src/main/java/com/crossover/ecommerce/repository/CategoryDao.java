package com.crossover.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.crossover.ecommerce.domain.Category;

public interface CategoryDao extends Repository<Category, Byte> {

	List<Category> findAll();

}
