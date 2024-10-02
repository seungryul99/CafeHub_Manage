package com.CafeHub.Manage.security.config;


import com.CafeHub.Manage.handler.LoginFailureHandler;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/login", "/signup", "/error","/").permitAll()
                        .anyRequest().authenticated()
                );

        // 커스텀 로그인 폼 추가 , + 추후 로그인 실패에 대한 처리를 담당할 에러 핸들러 추가할 예정
        http
                .formLogin((auth)->auth.loginPage("/login")
                        .failureHandler(loginFailureHandler())
                        .permitAll()
                );

        http
                .logout((auth)->auth.logoutUrl("/logout")
                        
                        // 세션 삭제후 JSESSIONID 쿠키도 삭제해줌, 로그아웃 핸들러, 성공시 모두 한번에 여기 안에서 구현 한거임
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession(false);
                            if(session != null){
                                session.invalidate();
                            }
                        })
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.sendRedirect("/login");
                        })
                        .deleteCookies("JSESSIONID"));


        // 일단 csrf 생각 안할 예정, 이건 api 서버가 아니라서 csrf 생각을 해봐야함
        http
                .csrf((auth)->auth.disable()
                );

        // 동시 접속 막을거임, 다중 로그인 시 기존 로그인된 사용자 로그아웃 시킴
        http
                .sessionManagement((auth)->auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                );

        // 이건 일단 임시 세션 고정 생각해 봐야 함
        http
                .sessionManagement((auth)->auth
                        .sessionFixation().changeSessionId()
                );

        return http.build();
    }


}
