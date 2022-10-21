package com.example.wakiserver.config;

import com.example.wakiserver.auth.JwtAuthenticationFilter;
import com.example.wakiserver.auth.JwtTokenProvider;
import com.example.wakiserver.auth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PrincipalOauth2UserService principalOauth2UserService;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
                .authorizeRequests()
                    .antMatchers("/user/**").authenticated()
                    .antMatchers("/manager/**").access("hasRole('MANAGER') or hasRole('ADMIN')")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                .and()
//               .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
//                .logout()					// logout할 경우
//                    .logoutUrl("/logout")			// 로그아웃을 처리할 URL 입력
//                    .logoutSuccessUrl("/")	// 로그아웃 성공 시 "/"으로 이동
//               .and()
                    .oauth2Login()				// OAuth2기반의 로그인인 경우
//                    .loginPage("/loginForm")		// 인증이 필요한 URL에 접근하면 /loginForm으로 이동
                    .defaultSuccessUrl("/")			// 로그인 성공하면 "/" 으로 이동
                    .failureUrl("/loginForm")		// 로그인 실패 시 /loginForm으로 이동
                    .userInfoEndpoint()			// 로그인 성공 후 사용자정보를 가져온다
                    .userService(principalOauth2UserService);	//사용자정보를 처리할 때 사용한다
    }
}
