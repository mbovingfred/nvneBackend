package com.nvne.webbackend;

import com.nvne.webbackend.entities.Entreprise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

}
