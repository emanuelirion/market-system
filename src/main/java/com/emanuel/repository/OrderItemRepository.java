package com.emanuel.repository;

import com.emanuel.modal.OrderItem;
import com.emanuel.payload.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Long> {
}
