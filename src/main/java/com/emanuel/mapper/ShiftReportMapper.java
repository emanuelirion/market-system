package com.emanuel.mapper;

import com.emanuel.modal.Order;
import com.emanuel.modal.Product;
import com.emanuel.modal.Refund;
import com.emanuel.modal.ShiftReport;
import com.emanuel.payload.dto.OrderDTO;
import com.emanuel.payload.dto.ProductDTO;
import com.emanuel.payload.dto.RefundDTO;
import com.emanuel.payload.dto.ShiftReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static ShiftReportDTO toDTO(ShiftReport  entity) {
        return ShiftReportDTO.builder()
                .id(entity.getId())
                .shiftEnd(entity.getShiftEnd())
                .shiftStart(entity.getShiftStart())
                .totalSales(entity.getTotalSales())
                .totalOrders(entity.getTotalOrders())
                .totalRefunds(entity.getTotalRefunds())
                .netSales(entity.getNetSales())
                .totalOrders(entity.getTotalOrders())
                .cashier(UserMapper.toDTO(entity.getCashier()))
                .cashierId(entity.getCashier().getId())
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .totalSellingProducts(mapProducts(entity.getTotalSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();

    }

    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
        if(refunds == null || refunds.isEmpty()) {
            return null;
        }
        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<Product> totalSellingProducts) {
        if(totalSellingProducts == null || totalSellingProducts.isEmpty()) {
            return null;
        }
        return totalSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    private static List<OrderDTO> mapOrders(List<Order> recentOrders) {
        if(recentOrders == null || recentOrders.isEmpty()) {
            return null;
        }
        return recentOrders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }


}
