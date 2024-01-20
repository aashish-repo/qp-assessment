package com.qpassessment.repository;

import com.qpassessment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByUserIdAndStatus(Integer userId, String status);
}
