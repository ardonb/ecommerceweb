package com.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.common.entity.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}