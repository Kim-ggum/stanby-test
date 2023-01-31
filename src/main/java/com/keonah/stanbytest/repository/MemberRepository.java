package com.keonah.stanbytest.repository;

import com.keonah.stanbytest.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByNo(String no);

    boolean existsByNameAndTeam(String name, String team);
    boolean existsByNo(String no);

}
