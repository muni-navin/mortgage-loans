package com.org.mortgage.loans.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        OpenApiConfiguration.class
})
public class AppConfig {

}
