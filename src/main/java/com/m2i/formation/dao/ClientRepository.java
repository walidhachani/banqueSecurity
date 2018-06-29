package com.m2i.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.formation.entites.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
