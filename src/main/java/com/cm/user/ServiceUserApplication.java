package com.cm.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cm.user.security.jwt.JwtConfig;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.cm.user")
public class ServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUserApplication.class, args);
	}

	/*
	 * @Bean BCryptPasswordEncoder getBCPE() { return new BCryptPasswordEncoder(); }
	 */

    @Bean
  	public JwtConfig jwtConfig() {
    	   return new JwtConfig();
  	}

//    @Bean
//    public WebMvcConfigurer corsConfigure(){
//        return  new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*");
//            }
//        };
//    }

}
