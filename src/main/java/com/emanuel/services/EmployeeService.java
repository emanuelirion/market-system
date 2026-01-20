package com.emanuel.services;

import com.emanuel.domain.UserRole;
import com.emanuel.modal.User;
import com.emanuel.payload.dto.UserDto;

import java.util.List;

public interface EmployeeService {

    UserDto createStoreEmployee(UserDto employeeDto, Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto employeeDto, Long branchId) throws Exception;
    User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception;
    List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception;


}
