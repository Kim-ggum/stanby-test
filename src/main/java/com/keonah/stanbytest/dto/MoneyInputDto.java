package com.keonah.stanbytest.dto;

import com.keonah.stanbytest.entity.MemberEntity;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class MoneyInputDto {

    @NotBlank(message = "input member")
    private String member;

    @NotNull(message = "input amount")
    private Long amount;

}
