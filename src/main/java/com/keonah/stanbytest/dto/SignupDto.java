package com.keonah.stanbytest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SignupDto {
    @NotBlank(message = "아이디로 사용할 이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String id;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "영어(대문자 혹은 소문자), 숫자, 특수문자를 각각 하나씩 포함하여 8글자 이상 입력해주세요.")
    private String pw;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

}
