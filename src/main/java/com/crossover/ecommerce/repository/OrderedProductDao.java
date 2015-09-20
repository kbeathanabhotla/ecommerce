package com.crossover.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.ecommerce.domain.OrderedProduct;
import com.crossover.ecommerce.domain.OrderedProductId;

public interface OrderedProductDao extends JpaRepository<OrderedProduct, OrderedProductId> {

}
