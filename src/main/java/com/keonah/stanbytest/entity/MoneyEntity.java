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
    private long no;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = MemberEntity.class)
    @JoinColumn(name = "member_no", nullable = false)
    private long member;

    @Column(nullable = false)
    private long amount;

}
