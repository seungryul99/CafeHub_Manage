package com.CafeHub.Manage.Admin.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class SignupRequest {
    
        @NotBlank(message = "아이디를 입력해주세요")
        @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "길이 8~16, 알파벳+숫자만 가능")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&+=])(?=\\S+$).{8,16}$", message = "길이 8~16, 알파벳 대소문자, 특수문자, 숫자 포함")
        private String password;

        @NotBlank(message = "이름을 입력해주세요")
        @Pattern(regexp = "^[가-힣]{1,8}$", message = "길이 1~8, 한글만 가능")
        private String name;

        @NotBlank(message = "인증 코드를 입력해주세요")
        private String code;
    }
