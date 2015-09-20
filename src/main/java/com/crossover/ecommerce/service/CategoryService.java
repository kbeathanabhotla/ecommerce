package com.crossover.ecommerce.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.ecommerce.domain.Category;
import com.crossover.ecommerce.repository.CategoryDao;

@Service
@Transactional
public class CategoryService {

	@Resource
	private CategoryDao categoryDao;

	public List<Category> getAll() {
		return categoryDao.findAll();
	}

}
