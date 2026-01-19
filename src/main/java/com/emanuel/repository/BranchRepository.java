package com.emanuel.repository;

import com.emanuel.modal.Branch;
import com.emanuel.payload.dto.BranchDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository  extends JpaRepository<Branch, Long> {

    List<Branch> findByStoreId(Long storeId);

    Long id(Long id);
}
