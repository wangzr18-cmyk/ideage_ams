package com.ideage.ams.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideage.ams.common.Constants;
import com.ideage.ams.config.filters.AmsAuthFilter;
import com.ideage.ams.config.handler.TokenToUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @description コンフィグレーション
 * @author zhen.cheng
 */
@Configuration
public class AmsWebMvcConfigurer implements WebMvcConfigurer {

	@Autowired
    private TokenToUserMethodArgumentResolver tokenUserMethodArgumentResolver;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final UserDetailsService userDetailsService;

    private final PersistentTokenRepository tokenRepository;

    @Autowired
    public AmsWebMvcConfigurer(@Qualifier("amsUserDetailsService") UserDetailsService userDetailsService,
                               @Qualifier("amsPersistentTokenRepository") PersistentTokenRepository tokenRepository) {
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }
    /**
     * TokenToUser
     *
     * @param argumentResolvers
     */
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenUserMethodArgumentResolver);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:"+ Constants.FILE_UPLOAD_PATH);
    }

    /**
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(
                        amsAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .authorizeHttpRequests(configurer ->
                        configurer
                                //.requestMatchers("/employee/**").hasRole("Administrator")
                                .requestMatchers(
                                        "/dist/**",
                                        "/plugins/**",
                                        "/authenticate",
                                        "/login"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .permitAll()
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .rememberMe( rmf -> rmf
                        .key("AmsSecretKey")
                        .tokenRepository(tokenRepository)
                        .tokenValiditySeconds(30 * 24 * 60 * 60) // 30天有效期
                        .userDetailsService(this.userDetailsService)
                        .rememberMeCookieName("remember-me")
                        .useSecureCookie(true)
                        )
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .deleteCookies("JSESSIONID","remember-me")
                        .permitAll()
                );
        // .exceptionHandling(configurer ->
        //          configurer.accessDeniedPage("/error")
        //  );

        return http.build();
    }

    /**
     * カスタマイズ認証フィルターのインスタンス生成
     * @return　認証フィルターのインスタンス
     * @throws Exception
     */
    public AmsAuthFilter amsAuthenticationFilter() throws Exception {

        AmsAuthFilter filter = new AmsAuthFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setRememberMeServices(rememberMeServices());
        return filter;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    /**
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public RememberMeServices rememberMeServices() {
        return new PersistentTokenBasedRememberMeServices(
                "AmsSecretKey",
                userDetailsService,
                tokenRepository
        );
    }
}