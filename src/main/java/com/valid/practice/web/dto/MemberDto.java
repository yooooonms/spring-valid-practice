package com.valid.practice.web.dto;

import com.valid.practice.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MemberDto {

    @NotBlank(message = "필수 정보입니다.")
    @Pattern(regexp = "^[a-z0-9]{6,20}$", message = "6 ~ 20자의 영어소문자/숫자 조합만 가능합니다.")
    private String identifier;

    @NotBlank(message = "필수 정보입니다.")
    @Pattern(regexp =  "^[a-zA-Z0-9~!@#$%^&*()\\-_+=\\\\]{8,20}$", message = "공백을 포함하지 않는 영어 대,소문자, 특수문자, 숫자로 이루어진 10 ~ 20자만 입력가능합니다.")
    private String password;

    @NotBlank(message = "필수 정보입니다.")
    @Email(message = "형식에 맞는 이메일을 입력해주세요")
    private String email;

    @Override
    public String toString() {
        return "MemberDto{" +
                "identifier='" + identifier + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Member toEntity() {
        return Member.builder()
                .identifier(identifier)
                .password(password)
                .email(email)
                .build();
    }
}
