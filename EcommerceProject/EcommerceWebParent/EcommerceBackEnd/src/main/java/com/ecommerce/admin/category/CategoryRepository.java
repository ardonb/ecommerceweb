package com.ecommerce.admin.category;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecommerce.common.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

}
