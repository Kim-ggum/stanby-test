package com.keonah.stanbytest.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class ExpenditureListDTO {

    // 회비 사용 내역 조회를 위한 DTO
    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))", message = "올바른 형식으로 입력해주세요.")
    private String startDate;

    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))", message = "올바른 형식으로 입력해주세요.")
    private String endDate;

}
