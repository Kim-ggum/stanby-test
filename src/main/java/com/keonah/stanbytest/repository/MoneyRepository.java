package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.entity.MoneyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MoneyRepository extends JpaRepository<MoneyEntity, Long> {

    MoneyEntity findTopByOrderByCreatedDate();

    Page<MoneyEntity> findAllByCreatedDateBetweenAndMember(LocalDate startDate, LocalDate endDate, String member, Pageable pageable);

    Page<MoneyEntity> findAllByCreatedDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
