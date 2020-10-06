package com.nvne.webbackend;

import com.nvne.webbackend.entities.AutoEntrepreneur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AutoEntrepreneurRepository extends JpaRepository<AutoEntrepreneur, Long> {

}
