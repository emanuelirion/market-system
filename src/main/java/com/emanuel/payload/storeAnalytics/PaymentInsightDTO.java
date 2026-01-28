package com.emanuel.payload.storeAnalytics;


import com.emanuel.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInsightDTO {
    private PaymentType paymentMethod;
    private Double totalAmount;
}
