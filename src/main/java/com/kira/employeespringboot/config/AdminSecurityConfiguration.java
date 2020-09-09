package com.kira.employeespringboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@Order(1)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/employee/**").permitAll().and().antMatcher("/support/admin/**").
                addFilter(getDigestAuthenticationFilter()).exceptionHandling()
                .authenticationEntryPoint(getDigestEntryPoint())
                .and().authorizeRequests().antMatchers("/support/admin/**").hasRole("ADMIN");
    }

    public DigestAuthenticationFilter getDigestAuthenticationFilter() throws Exception {
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setUserDetailsService(userDetailsServiceBean());
        filter.setAuthenticationEntryPoint(getDigestEntryPoint());

/*filter.setPasswordAlreadyEncoded(true);
        filter.setCreateAuthenticatedToken(true);*/

        return filter;
    }

    private DigestAuthenticationEntryPoint getDigestEntryPoint(){
        DigestAuthenticationEntryPoint entryPoint= new DigestAuthenticationEntryPoint();
        entryPoint.setRealmName("admin-digest-realm");
        entryPoint.setKey("noidea_+");
        return entryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("password")
                .roles("ADMIN");

    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}

