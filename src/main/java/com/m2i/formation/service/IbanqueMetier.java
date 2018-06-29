package com.m2i.formation.service;

import org.springframework.data.domain.Page;

import com.m2i.formation.entites.Compte;
import com.m2i.formation.entites.Operation;

public interface IbanqueMetier {
	
	public Compte consulterCompte(String codeCpte) ; 
	public void verser(String codeCpte , double montant) ;
	public void retirer(String codeCpte , double montant) ; 
	public void virement(String codeCpte1 , String codeCpte2 , double montant);
	public Page<Operation> listOperation(String codeCpte , int page , int size);

}
