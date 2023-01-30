package com.keonah.stanbytest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class MemberCreateDto {

    @NotBlank(message = "input id")
    @Size(min = 5, max = 5)
    private String id; // 사원번호

    @NotBlank(message = "input name")
    private String name; // 이름

    @NotBlank(message = "input position")
    private String position; // 직책

    @NotBlank(message = "input team")
    private String team; // 부서명

    @NotBlank(message = "input joinDate")
    @Size(min = 10, max = 10)
    private String joinDate;

}
