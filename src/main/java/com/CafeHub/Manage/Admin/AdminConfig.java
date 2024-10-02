package com.CafeHub.Manage.Admin;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AdminConfig {

    @Value("${spring.secretcode}")
    private String secretCode;
}
