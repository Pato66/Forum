package com.example.forum.config;


import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableAutoConfiguration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/registration", "/index").
			permitAll().antMatchers("/admin", "/users","/statistics").hasRole("ADMIN")
			.anyRequest().authenticated().and().
			formLogin().loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/index").permitAll();
		
		http.exceptionHandling().accessDeniedPage("/403");
		

	}
    
	@Autowired
	DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    
    		auth.jdbcAuthentication().dataSource(dataSource)
    				.usersByUsernameQuery("select login,password, enabled from users where login=?")
    				.authoritiesByUsernameQuery("select login, role from user_roles where login=?");      
    }
    
    
    @Configuration
    public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {      
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**")
            .addResourceLocations("src/main/")
            .setCachePeriod(0);
        }
    }
    
}