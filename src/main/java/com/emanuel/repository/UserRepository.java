package com.emanuel.repository;

import com.emanuel.modal.Store;
import com.emanuel.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//busca e salva no banco de dados
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
