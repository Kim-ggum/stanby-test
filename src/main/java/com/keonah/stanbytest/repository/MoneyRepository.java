package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.entity.MoneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<MoneyEntity, Long> {
}
