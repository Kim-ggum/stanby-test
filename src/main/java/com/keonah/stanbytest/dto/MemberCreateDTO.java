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
public class MemberCreateDTO {

    // 회원 추가를 위한 DTO
    @NotBlank(message = "사원 번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{5}", message = "올바른 형식으로 입력해주세요.")
    private String no; // 사원번호

    @NotBlank(message = "이름을 입력해주세요.")
    private String name; // 이름

    @NotBlank(message = "직책을 입력해주세요.")
    private String position; // 직책

    @NotBlank(message = "부서명을 입력해주세요.")
    private String team; // 부서명

    @NotBlank(message = "입사일을 입력해주세요.")
    @Pattern(regexp = "(19|20)\\d{2}\\.((11|12)|(0?(\\d)))\\.(30|31|((0|1|2)?\\d))", message = "올바른 형식으로 입력해주세요.")
    private String joinDate;

}
