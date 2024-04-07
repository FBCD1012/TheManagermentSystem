package com.example.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


//构建安全配置文件
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    //创建对应的加密类操作
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //构建持久化TOKEN的操作
    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository=new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    //构建安全过滤链进行操作
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,PersistentTokenRepository persistentTokenRepository) throws Exception {
        return httpSecurity.authorizeHttpRequests(auth->{
            //静态目录下面的所有子类以及相关资源文件
            auth.requestMatchers("/static/**").permitAll();
            auth.anyRequest().authenticated();
        }).formLogin(conf->{
            conf.loginPage("/");
            conf.loginProcessingUrl("/loginInfo");
            conf.defaultSuccessUrl("/userPage");
            conf.failureUrl("/failure");
            conf.permitAll();
            conf.usernameParameter("username");
            conf.passwordParameter("password");
        }).logout(conf->{
            conf.logoutUrl("/logout");
            conf.logoutSuccessUrl("/login");
            conf.permitAll();
        }).rememberMe(conf->{
            conf.rememberMeParameter("rememberMyUserInfo");
            conf.tokenRepository(persistentTokenRepository);
            //二十四小时自动过期
            conf.tokenValiditySeconds(3600*24);
        })
        .csrf(AbstractHttpConfigurer::disable)
        .build();
    }
}
