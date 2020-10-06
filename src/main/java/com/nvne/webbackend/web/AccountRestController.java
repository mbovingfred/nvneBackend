package com.nvne.webbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nvne.webbackend.entities.AppUser;
import com.nvne.webbackend.service.AccountService;

@RestController
public class AccountRestController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm userForm) {
		if (!userForm.getPassword().equals(userForm.getPassword()))
			throw new RuntimeException("You must Confirm your password");
		AppUser appUser = accountService.findUserByUsername(userForm.getUsername());
		if (appUser != null)
			throw new RuntimeException("This user already exists");
		appUser = new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		return accountService.saveUser(appUser);
	}
}
