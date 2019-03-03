package pers.vergil.restfulcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.vergil.restfulcrud.component.LoginHandlerInterceptor;
import pers.vergil.restfulcrud.component.MYLocaleResolver;

import javax.validation.groups.Default;

@Configuration

public class MyMvcConfig implements WebMvcConfigurer {

    //    注册拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/static/**","/webjars/**","/asserts/**");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/aaa").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("main.html").setViewName("dashboard");

    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MYLocaleResolver();
    }

}
