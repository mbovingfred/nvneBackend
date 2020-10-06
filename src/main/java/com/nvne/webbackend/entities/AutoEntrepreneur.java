package com.nvne.webbackend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoEntrepreneur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String noms;
	private String prenoms;
	private String niveauEtude;
	private String email;
	private String cv;
}
