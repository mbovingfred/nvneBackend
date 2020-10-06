package com.nvne.webbackend.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvne.webbackend.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRoleName(String roleName);
}
