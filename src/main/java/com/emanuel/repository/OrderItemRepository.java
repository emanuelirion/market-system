package com.emanuel.repository;

import com.emanuel.modal.Category;
import com.emanuel.modal.OrderItem;
import com.emanuel.payload.branchAnalytics.CategorySalesDTO;
import com.emanuel.payload.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Long> {


    @Query("""
select p.id, p.name, sum(oi.quantity) as totalProduct from OrderItem oi
 join oi.product p
 join oi.order o
 where o.branch.id=:branchId
 GROUP BY p.id, p.name
 order by totalProduct desc
""")
    List<Object[]> getTopProductsByQuantity(@Param("branchId") Long branchId);

    @Query("""
select c.name, sum(oi.quantity * oi.price) as totalAmount, sum(oi.quantity) from OrderItem oi
 join oi.product p
 join oi.order o
 join p.category c
 where o.branch.id=:branchId
 AND o.createdAt BETWEEN :start AND :end
 GROUP BY c.name
 order by totalAmount desc
""")
    List<Object[]> getCategoryWiseSales(@Param("branchId") Long branchId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
/*
    @Query("""
select new com.emanuel.payload.branchAnalytics.CategorySalesDTO(
p.category.name,
sum(oi.quantity * p.SellingPrice),
Long(0)
)
From OrderItem oi
join oi.product p
where p.store.storeAdmin.id = :storeAdminId
group by p.category.name
""")
    List<CategorySalesDTO> getSalesGroupByCategory(@Param("storeAdminId") Long storeAdminId);

 */

}
