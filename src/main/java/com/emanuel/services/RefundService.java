package com.emanuel.services;

import com.emanuel.modal.Refund;
import com.emanuel.payload.dto.BranchDTO;
import com.emanuel.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDTO createRefund(RefundDTO refund)throws Exception;
    List<RefundDTO> getAllRefunds()throws Exception;
    List<RefundDTO> getRefundByCashier(Long cashierId)throws Exception;
    List<RefundDTO> getRefundByShiftReport(Long shiftBranchId)throws Exception;
    List<RefundDTO> getRefundByCashierAndBranch(Long cashierId, LocalDateTime startDate, LocalDateTime endDate)throws Exception;

    List<RefundDTO> getRefundByBranch(Long branchId)throws Exception;
    RefundDTO getRefundById(Long refundId)throws Exception;
    void deleteRefundById(Long refundId)throws Exception;

}
