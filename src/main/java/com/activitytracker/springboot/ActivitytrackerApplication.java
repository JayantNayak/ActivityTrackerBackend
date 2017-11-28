package com.activitytracker.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.activitytracker.springboot.security.BasicAuthenticationPoint;
import com.activitytracker.springboot.security.CustomJdbcDaoImpl;
import com.activitytracker.springboot.util.ApiPath;

//@SpringBootApplication
//@ComponentScan("com.actitvitytrack.model")
//@EnableJpaRepositories
//@ComponentScan("com.actitvitytrack.model,com.actitvitytracker.springboot")


/*@Configuration
@ComponentScan
@EnableAutoConfiguration*/

//@EntityScan("com.actitvitytracker.model")
@SpringBootApplication
@EnableWebSecurity
public class ActivitytrackerApplication extends WebSecurityConfigurerAdapter {
	 @Autowired
	DataSource dataSource;
	 
	@Autowired  
	private BasicAuthenticationPoint basicAuthenticationPoint;  
	
	@Autowired 
	UserDetailsService userDetailsService;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitytrackerApplication.class, args);
		
	}
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	        http.authorizeRequests().antMatchers(
	        		ApiPath.USER_API+"/checkEmailIdExist",
	        		ApiPath.USER_API+"/createUser",
	        		ApiPath.USER_API+"/createAnOneTimeAdminUser").permitAll()
	       // http.authorizeRequests().antMatchers("/", "/api/**").permitAll()
	        .antMatchers("/",ApiPath.USER_API+"/**")
	        .access("hasAuthority('USER')")
	        //.permitAll()
	        
	        //.anyRequest().authenticated();
	        .anyRequest().access("hasAuthority('ADMIN')");
	       http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
	}
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder(){
		return new BCryptPasswordEncoder();
		 
	}
	
	/* @Autowired
	    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
	     
		 auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select id, password, 1 as enabled from app_user where id=?")
		 .authoritiesByUsernameQuery("select parent_user_id, role from user_role where parent_user_id=?");
		 
	    }*/
	 
	 @Bean(name="userDetailsService")
	 public UserDetailsService  userDetailsService(){
		 //JdbcDaoImpl jdbcImpl= new JdbcDaoImpl();
		 CustomJdbcDaoImpl jdbcImpl= new CustomJdbcDaoImpl();
		 jdbcImpl.setDataSource(dataSource);
		 
		 jdbcImpl.setUsersByUsernameQuery("select emailid, password, 1 as enabled,id from app_user where emailid=?");
		 jdbcImpl.setAuthoritiesByUsernameQuery("select parent_user_id, role from user_role where parent_user_id=?");
		 
		 
		 return jdbcImpl;
		 
	 }

}
