package com.keonah.stanbytest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "money")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(length = 5, nullable = false)
    private String member; // 회원 (사원 번호)

    @Column(nullable = false)
    private Long amount; // 금액

}
