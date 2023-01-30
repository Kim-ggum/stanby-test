package com.keonah.stanbytest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class MemberUpdateDto {

    @NotBlank(message = "input name")
    private String name; // 이름

    @NotBlank(message = "input position")
    private String position; // 직책

    @NotBlank(message = "input team")
    private String team; // 부서명

}
