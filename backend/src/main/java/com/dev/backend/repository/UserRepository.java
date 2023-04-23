package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

    User findByEmailAndCodRecoveryPassword(String email, String codRecoveryPassword);
}
