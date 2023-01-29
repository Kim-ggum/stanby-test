package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findById(String id);
    AdminEntity findByName(String name);

    // 아이디, 이름 중복 검사
    boolean existsById(String id);
    boolean existsByName(String name);
}
