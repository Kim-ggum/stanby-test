package com.keonah.stanbytest.entity;

import com.keonah.stanbytest.util.RoleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(length = 5, nullable = false)
    private String id; // 사원번호

    @Column(length = 30, nullable = false)
    private String name; // 이름

    @Column(length = 255, nullable = false)
    private String position; // 직책

    @Column(length = 255, nullable = false)
    private String team; // 부서명

    @Column(length = 10, nullable = false)
    private String joinDate;

}
