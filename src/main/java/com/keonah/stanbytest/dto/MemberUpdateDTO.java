package com.keonah.stanbytest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class MemberUpdateDTO {

    // 회원 정보 수정을 위한 DTO
    @NotBlank(message = "이름을 입력해주세요.")
    private String name; // 이름

    @NotBlank(message = "직책을 입력해주세요.")
    private String position; // 직책

    @NotBlank(message = "부서명을 입력해주세요.")
    private String team; // 부서명

}
