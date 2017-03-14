package com.milkyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.milkyway.dao.UserDAO;
import com.milkyway.model.Response;
import com.milkyway.utils.EmailSender;
import com.milkyway.validator.RequestValidator;

@SpringBootApplication
public class Application {
	
	@Bean
	public RequestValidator requestValidator(){
		return new RequestValidator();
	}
	
	@Bean
	public Response response(){
		return new Response();
	}
	
	@Bean
	public UserDAO userDAO(){
		return new UserDAO();
	}
	
	@Bean
	public EmailSender emailSender(){
		return new EmailSender();
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
