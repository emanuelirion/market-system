package com.emanuel.modal;

import com.emanuel.domain.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSummary {

    private PaymentType type;
    private Double totalAmount;
    private int transactionCount;
    private double percentage;

    public PaymentSummary(PaymentType type, Double amount, int count, long l) {
    }
}
