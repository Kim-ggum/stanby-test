package com.keonah.stanbytest.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ExpenditureDto {

    @NotBlank(message = "input purpose")
    private String purpose;

    @NotNull(message = "input amount")
    private Long amount;

    @NotBlank(message = "input date")
    private String date;
}
