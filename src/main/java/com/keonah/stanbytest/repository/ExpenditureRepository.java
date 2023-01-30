package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.entity.ExpenditureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenditureRepository extends JpaRepository<ExpenditureEntity, Long> {
}
