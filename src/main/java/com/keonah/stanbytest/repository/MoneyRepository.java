package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.dto.MoneyListDto;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.entity.MoneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MoneyRepository extends JpaRepository<MoneyEntity, Long> {

    MoneyEntity findTopByOrderByCreatedDate();

    List<MoneyEntity> findAllByCreatedDateBetweenAndMember(LocalDate startDate, LocalDate endDate, String member);

    List<MoneyEntity> findAllByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

}
