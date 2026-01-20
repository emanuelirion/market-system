package com.emanuel.payload.dto;

import com.emanuel.domain.PaymentType;
import com.emanuel.modal.Branch;
import com.emanuel.modal.Customer;
import com.emanuel.modal.OrderItem;
import com.emanuel.modal.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {

    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;
    private Long customerId;

    private BranchDTO branch;
    private UserDto cashier;

    private PaymentType paymentType;


    private Customer customer;

    private List<OrderItemDTO> items;
}
