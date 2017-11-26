package com.activitytracker.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.activitytracker.springboot.security.BasicAuthenticationPoint;

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
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitytrackerApplication.class, args);
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	        http.authorizeRequests().antMatchers("/", "/api/**","/admin/**").permitAll()
	        .anyRequest().authenticated();
	        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
	}
	/*@Autowired  
	   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {  
	     auth.inMemoryAuthentication().withUser("chandana").password("chandana").roles("USER");  
	   }*/
	 @Autowired
	    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
	        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username, password, 1 as enabled from user where username=?")
	                .authoritiesByUsernameQuery("select username, role from user where username=?");
	    }

}
