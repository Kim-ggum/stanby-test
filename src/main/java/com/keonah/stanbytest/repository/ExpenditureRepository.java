package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.entity.ExpenditureEntity;
import com.keonah.stanbytest.entity.MoneyEntity;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Repository
public interface ExpenditureRepository extends JpaRepository<ExpenditureEntity, Long> {

    ExpenditureEntity findTopByOrderByDate();

    List<ExpenditureEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}