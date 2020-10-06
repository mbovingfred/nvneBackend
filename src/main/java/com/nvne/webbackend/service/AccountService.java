package com.nvne.webbackend.service;

import com.nvne.webbackend.entities.AppRole;
import com.nvne.webbackend.entities.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUse(String username, String roleName);
	public AppUser findUserByUsername(String username);
}
