 package com.kira.employeespringboot.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    
    private UserDetailsService userDetailsService;

    public SecurityConfiguration(@Qualifier("customerUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception{
//        http.headers().frameOptions().sameOrigin();
        // Not recommended if you dont know what you are doing
//        http.csrf().disable().headers().defaultsDisabled();
       // http.headers().cacheControl().disable();
        //http.csrf().disable();
        //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());


/* http.headers().frameOptions().disable()
                .addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS","ALLOW_FROM some.com"))
                .and()
                .authorizeRequests().anyRequest().authenticated()/
                .and()
                .formLogin();*//*


        */
/*http.headers().frameOptions().disable()
                .addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS","ALLOW_FROM some.com"))
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().and().headers().contentSecurityPolicy("script-src 'self' https://yourdomain.com");*//*


        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic().and().logout();*/
       /* http.headers().httpPublicKeyPinning()
                .includeSubDomains(true)
                .reportUri("<uri")
                .addSha256Pins("<key>");*/
//        http.antMatcher("/entry/**").authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
//        Url based and role based
        http.csrf().disable();
  http.authorizeRequests().antMatchers("/employee/**").permitAll().and().
          authorizeRequests().antMatchers("/secure/rest/**").hasAnyRole("ADMIN")
          .anyRequest().authenticated().and().formLogin().permitAll();
    }

//    public static NoOpPasswordEncoder passwordEncoder(){
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        /*DelegatingPasswordEncoder passwordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());*/
        return  new BCryptPasswordEncoder();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        in-memory authentication
        /*        auth.inMemoryAuthentication().withUser("krish").password("noidea").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("mystery").password("idea").roles("USER");*/

//      UserDetailsService
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
//      LDAP
       /* auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou-groups")
                .contextSource(contextSource()).passwordCompare().passwordEncoder((new LdapShaPasswordEncoder()))
                .passwordAttribute("userPassword");*/
    }

//    LDAP
    /*@Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389"),
                "dc=springframework,dc=org");
    }*/
}