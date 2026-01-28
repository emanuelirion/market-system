package com.emanuel.repository;

import com.emanuel.modal.Branch;
import com.emanuel.modal.Order;
import com.emanuel.modal.User;
import com.emanuel.payload.storeAnalytics.PaymentInsightDTO;
import com.emanuel.payload.storeAnalytics.TimeSeriesPointDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);
    List<Order> findByBranchId(Long productId);
    List<Order> findByCashierId(Long cashierId);
    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId, LocalDateTime from, LocalDateTime to);
    List<Order> findByCashierAndCreatedAtBetween(User cashier, LocalDateTime from, LocalDateTime to);
    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);

    //analytics
    @Query("""
    select sum(o.totalAmount)
    FROM Order o
    Where o.branch.id = :branchId
    And o.createdAt BETWEEN :start AND :end
""")
    Optional<BigDecimal> getTotalSalesBetween(@Param("branchId") Long branchId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


    @Query("""
    Select u.id, u.fullName, Sum(o.totalAmount) as totalRevenue
    From Order o
    Join o.cashier u
    where o.branch.id = :branchId
    GROUP BY u.id, u.fullName
    order by totalRevenue DESC
""")
    List<Object[]> getTopCashierByRevenue(@Param("branchId") Long branchId);

    @Query("""
    select count(o) from Order o
    where o.branch.id=:branchId
    And DATE(o.createdAt)=:date
    """)
    int countOrdersByBranchAndDate(@Param("branchId") Long branchId, @Param("date") LocalDate date);
    @Query("""
    select count(distinct o.cashier.id) from Order o
    where o.branch.id=:branchId
    And DATE(o.createdAt)=:date
    """)
    int countDistinctCashierByBranchAndDate(@Param("branchId") Long branchId, @Param("date") LocalDate date);


    @Query("""
select o.paymentType, sum(o.totalAmount), count(o) from Order o
where o.branch.id=:branchId
And DATE(o.createdAt)=:date
GROUP BY o.paymentType
""")

    List<Object[]> getPaymentBreakdownByMethod(@Param("branchId") Long branchId, @Param("date") LocalDate date);

    @Query("""
SELECT sum(o.totalAmount) FROM Order o
where o.branch.store.storeAdmin=:storeAdmin

""")
    Optional<Double> sumTotalSalesByStoreAdmin(@Param("storeAdminId") Long storeAdminId);

    @Query("""
SELECT count(o) FROM Order o
where o.branch.store.storeAdmin=:storeAdmin

""")
    Optional<Double> countByStoreAdmin(@Param("storeAdminId") Long storeAdminId);


    @Query("""
select o from Order o where  o.branch.store.storeAdmin=:storeAdmin
And o.createdAt BETWEEN :start AND :end
""")
    List<Order> FindAllByStoreAdminAndCreatedAtBetween(@Param("storeAdminId") Long storeAdminId,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
    @Query("""
select new com.emanuel.payload.storeAnalytics.TimeSeriesPointDTO(o.createdAt, sum(o.totalAmount))
from Order o where  o.branch.store.storeAdmin=:storeAdmin
And o.createdAt BETWEEN :start AND :end
GROUP BY o.createdAt
order by o.createdAt
""")
    List<TimeSeriesPointDTO> getDailySales(@Param("storeAdminId") Long storeAdminId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("""
select new com.emanuel.payload.storeAnalytics.PaymentInsightDTO(o.paymentType, sum(o.totalAmount))
from Order o where  o.branch.store.storeAdmin=:storeAdmin
GROUP BY o.paymentType

""")
    List<PaymentInsightDTO> getSalesByPaymentMethod(@Param("storeAdminId") Long storeAdminId);


}
