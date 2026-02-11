package com.viscovich.auth_service.repository;
import com.viscovich.auth_service.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser, Long> {
}
