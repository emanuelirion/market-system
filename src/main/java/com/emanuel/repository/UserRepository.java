package com.emanuel.repository;

import com.emanuel.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;


//busca e salva no banco de dados
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
