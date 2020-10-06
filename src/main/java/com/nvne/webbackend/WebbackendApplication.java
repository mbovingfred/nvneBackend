package com.nvne.webbackend;

import com.nvne.webbackend.entities.AppRole;
import com.nvne.webbackend.entities.AppUser;
import com.nvne.webbackend.entities.AutoEntrepreneur;
import com.nvne.webbackend.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//@EnableDiscoveryClient
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WebbackendApplication implements CommandLineRunner {

	@Autowired
	private AccountService accountService;
	@Autowired
	private AutoEntrepreneurRepository autoEntrepreneurRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebbackendApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		accountService.saveUser(new AppUser(null,"admin","admin",null));
		accountService.saveUser(new AppUser(null,"user","1234",null));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		accountService.addRoleToUse("admin", "USER");
		accountService.addRoleToUse("user", "USER");
		
		AutoEntrepreneur en = new AutoEntrepreneur(null, "test", "testprénom", "bac+5", "contact@nvne.com", "/cv");
		autoEntrepreneurRepository.save(en);
	}

}
