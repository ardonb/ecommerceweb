package com.ecommerce.admin.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecommerce.common.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
