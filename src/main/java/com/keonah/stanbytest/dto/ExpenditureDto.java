package com.keonah.stanbytest.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class ExpenditureDto {

    @NotBlank(message = "input purpose")
    private String purpose;

    @NotNull(message = "input amount")
    private Long amount;

    @NotBlank(message = "input date")
    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))")
    private String date;
}
