package com.emanuel.services.impi;

import com.emanuel.mapper.RefundMapper;
import com.emanuel.modal.Branch;
import com.emanuel.modal.Order;
import com.emanuel.modal.Refund;
import com.emanuel.modal.User;
import com.emanuel.payload.dto.RefundDTO;
import com.emanuel.repository.OrderRepository;
import com.emanuel.repository.RefundRepository;
import com.emanuel.services.RefundService;
import com.emanuel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImpi implements RefundService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final RefundRepository refundRepository;

    @Override
    public RefundDTO createRefund(RefundDTO refund) throws Exception {
        User cashier = userService.getCurrentUser();

        Order order = orderRepository.findById(refund.getOrderId()).orElseThrow(
                () -> new Exception("Order Not Found")
        );

        Branch branch =  order.getBranch();

        Refund createdRefund = Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .createdAt(refund.getCreatedAt())
                .build();

        Refund savedRefund=refundRepository.save(createdRefund);

        return RefundMapper.toDTO(savedRefund);
    }

    @Override
    public List<RefundDTO> getAllRefunds() throws Exception {
        return refundRepository.findAll().stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByShiftReport(Long shiftBranchId) throws Exception {
        return refundRepository.findByShiftReportId(shiftBranchId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashierAndBranch(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId, startDate, endDate).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) throws Exception {
        return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RefundDTO getRefundById(Long refundId) throws Exception {
        return refundRepository.findById(refundId).map(RefundMapper::toDTO).orElseThrow(
                () -> new Exception("Refund Not Found")
        );
    }

    @Override
    public void deleteRefundById(Long refundId) throws Exception {
        this.getRefundById(refundId);
        refundRepository.deleteById(refundId);

    }
}
