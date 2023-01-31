package com.keonah.stanbytest.dto;

import com.keonah.stanbytest.entity.MemberEntity;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class MoneyInputDto {

    @NotBlank(message = "사원번호를 입력해주세요.")
    private String member;

    @NotNull(message = "금액을 입력해주세요.")
    private Long amount;

}
