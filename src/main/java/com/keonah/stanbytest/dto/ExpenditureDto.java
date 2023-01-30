package com.keonah.stanbytest.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ExpenditureDto {

    @NotBlank(message = "input purpose")
    String purpose;

    @NotNull(message = "input amount")
    Long amount;

    @NotBlank(message = "input date")
    String date;
}
