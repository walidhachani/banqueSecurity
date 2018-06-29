package com.m2i.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.formation.entites.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{

}
