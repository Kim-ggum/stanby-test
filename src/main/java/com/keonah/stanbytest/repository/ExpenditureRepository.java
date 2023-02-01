package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.entity.ExpenditureEntity;
import com.keonah.stanbytest.entity.MoneyEntity;
import com.keonah.stanbytest.mapping.ExpenditureInfoMapping;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Repository
public interface ExpenditureRepository extends JpaRepository<ExpenditureEntity, Long> {

    ExpenditureEntity findTopByOrderByDate();

    Page<ExpenditureInfoMapping> findAllByDateBetweenOrderByDate(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
