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
    @Email(message = "input email")
    private String id;

    @NotBlank(message = "input password")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String pw;

    @NotBlank(message = "input name")
    private String name;

}
