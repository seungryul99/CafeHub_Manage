package com.CafeHub.Manage.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorCode;


        // 우선은 어떤 오류가 터져도 error = true를 반환하게함
        // 아이디가 존재하지 않거나 비밀번호가 틀렸거나 어떤 조건으로 에러가 발생했는지 노출하면 보안이 취약해 질거라고 생각함
        // 밸리데이션 관련해서는 추가가 되어야함
        // 추가적으로 로그인 관련 에러 처리는 더 손을 봐야함
        if (exception instanceof BadCredentialsException) {
            errorCode = "1";
        } else errorCode = "2";

        setDefaultFailureUrl("/login?error=" + errorCode);

        super.onAuthenticationFailure(request, response, exception);

    }
}
