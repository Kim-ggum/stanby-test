package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.mapping.MoneyInfoMapping;
import com.keonah.stanbytest.entity.MoneyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MoneyRepository extends JpaRepository<MoneyEntity, Long> {

    MoneyEntity findTopByOrderByCreatedDate();

    Page<MoneyInfoMapping> findAllByCreatedDateBetweenAndMemberOrderByCreatedDate(LocalDate startDate, LocalDate endDate, String member, Pageable pageable);
    Page<MoneyInfoMapping> findAllByCreatedDateBetweenOrderByCreatedDate(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
