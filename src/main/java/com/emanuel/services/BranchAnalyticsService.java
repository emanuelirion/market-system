package com.emanuel.services;

import com.emanuel.modal.PaymentSummary;
import com.emanuel.payload.branchAnalytics.*;

import java.time.LocalDate;
import java.util.List;

public interface BranchAnalyticsService {

    List<DailySalesDTO> getDailySalesCharts(Long branchId, int days);
    List<ProductPerformanceDTO> getTopProductsByQuantityWithPercentage(Long branchId);
    List<CashierPerformanceDTO> getTopCashierPerformanceByOrders(Long branchId);
    List<CategorySalesDTO> getCategoryWiseSalesBreakdown(Long branchId, LocalDate date);
    BranchDashboardOverviewDTO getBranchOverview(Long branchId);
    List<PaymentSummary> getPaymentMethodBreakdown(Long branchId, LocalDate date);
}
