package com.keonah.stanbytest.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class ExpenditureListDto {

    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))")
    private String startDate;

    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))")
    private String endDate;

}
