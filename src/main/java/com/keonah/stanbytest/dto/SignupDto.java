package com.keonah.stanbytest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public class SignupDto {
    @NotBlank(message = "아이디로 사용할 이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String pw;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

}
