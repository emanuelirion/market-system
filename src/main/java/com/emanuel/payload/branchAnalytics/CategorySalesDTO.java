package com.emanuel.payload.branchAnalytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class CategorySalesDTO {


    private String categoryName;
    private Double totalSales;
    private Long quantitySold;
}
