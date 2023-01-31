package com.keonah.stanbytest.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class ExpenditureDto {

    @NotBlank(message = "목적을 입력해주세요.")
    private String purpose;

    @NotNull(message = "금액을 입력해주세요.")
    private Long amount;

    @NotBlank(message = "날짜를 입력해주세요.")
    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))", message = "올바른 형식으로 입력해주세요.")
    private String date;
}
