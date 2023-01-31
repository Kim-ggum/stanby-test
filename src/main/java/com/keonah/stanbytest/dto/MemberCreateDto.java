package com.keonah.stanbytest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class MemberCreateDto {

    @NotBlank(message = "input id")
    @Pattern(regexp = "[0-9]{5}")
    private String no; // 사원번호

    @NotBlank(message = "input name")
    private String name; // 이름

    @NotBlank(message = "input position")
    private String position; // 직책

    @NotBlank(message = "input team")
    private String team; // 부서명

    @NotBlank(message = "input joinDate")
    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))")
    private String joinDate;

}
