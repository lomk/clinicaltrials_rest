package ua.com.clinicaltrials.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

/**
 * Created by Igor on 10-Jun-16.
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class ApplicationConfigurerAdapter extends WebMvcConfigurerAdapter {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer ) {
            configurer.enable();
    }


//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("utf-8");
//        return resolver;
//    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//
//    @Bean
//    public ReloadableResourceBundleMessageSource messageSource(){
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//    @Bean
//    public CookieLocaleResolver localeResolver(){
//        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.ENGLISH);
//        localeResolver.setCookieName("my-locale-cookie");
//        localeResolver.setCookieMaxAge(3600);
//        return localeResolver;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeInterceptor(){
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("lang");
//        return interceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeInterceptor());
//    }
//

}
