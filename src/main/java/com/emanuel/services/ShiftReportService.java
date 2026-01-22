package com.emanuel.services;

import com.emanuel.exception.UserException;
import com.emanuel.modal.ShiftReport;
import com.emanuel.payload.dto.ShiftReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDTO startShift()throws Exception;

    ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd)throws Exception;

    ShiftReportDTO getShiftReportById(Long id) throws Exception;

    List<ShiftReportDTO> getAllShiftReports() throws Exception;

    List<ShiftReportDTO> getShiftsReportsByBranchId(Long branchId) throws Exception;
    List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) throws Exception;

    ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception;

    ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;






}
