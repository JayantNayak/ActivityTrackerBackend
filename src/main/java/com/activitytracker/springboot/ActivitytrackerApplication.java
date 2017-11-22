package com.activitytracker.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.actitvitytracker.model.Exercise;
import com.actitvitytracker.model.Shooting;
import com.actitvitytracker.model.User;
import com.actitvitytracker.model.Yoga;

//@SpringBootApplication
//@ComponentScan("com.actitvitytrack.model")
//@EnableJpaRepositories
//@ComponentScan("com.actitvitytrack.model,com.actitvitytracker.springboot")


/*@Configuration
@ComponentScan
@EnableAutoConfiguration*/
@SpringBootApplication
@EntityScan("com.actitvitytracker.model")
public class ActivitytrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitytrackerApplication.class, args);
		
	}
	/*@Bean public Yoga  yogaBean(){
		   return new Yoga();
		}
	
	@Bean public Shooting  shootingBean(){
		   return new Shooting();
		}
	@Bean public Exercise  exerciseBean(){
		   return new Exercise();
		}
	@Bean public User  userBean(){
		   return new User();
		}*/
}