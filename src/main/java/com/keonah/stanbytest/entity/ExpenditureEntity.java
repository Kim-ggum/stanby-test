package com.keonah.stanbytest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenditure")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    @Column(nullable = false)
    String purpose; // 사용 목적

    @Column(nullable = false)
    Long amount; // 사용 금액

    @Column(nullable = false)
    LocalDate date; // 사용일

}
