package com.m2i.formation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.m2i.formation.dao.ClientRepository;
import com.m2i.formation.dao.CompteRepository;
import com.m2i.formation.dao.OperationRepository;
import com.m2i.formation.entites.Client;
import com.m2i.formation.entites.Compte;
import com.m2i.formation.entites.CompteCourant;
import com.m2i.formation.entites.CompteEpargne;
import com.m2i.formation.entites.Retrait;
import com.m2i.formation.entites.Versement;
import com.m2i.formation.service.IbanqueMetier;

@SpringBootApplication
public class MaBanqueApplication  implements CommandLineRunner{
	
	@Autowired
	ClientRepository clientRepository ;
	
	@Autowired
	CompteRepository compteRepository ; 
	
	@Autowired
	OperationRepository operationRepository ;
	
	@Autowired
	private IbanqueMetier ibanqueMetier ; 

	public static void main(String[] args) {
	 SpringApplication.run(MaBanqueApplication.class, args);

	
	}

	@Override
	public void run(String... args) throws Exception {
	Client c1=	clientRepository.save(new Client("walid", "walid@gmail.com")) ;
	Client c2=	clientRepository.save(new Client("skander", "skander@gmail.com")) ;
	
	
	Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 500, c1, 800)) ;
	Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 600, c2, 5.5)) ;
	
	
	
	operationRepository.save(new Versement(new Date(), 300, cp1)) ; 
	operationRepository.save(new Retrait(new Date(), 100, cp1)) ; 
	operationRepository.save(new Retrait(new Date(), 300, cp2)) ; 
	operationRepository.save(new Versement(new Date(), 700, cp2)) ; 
	
		
	ibanqueMetier.verser("c1", 20000);
	ibanqueMetier.virement("c1", "c2", 1000);
		
	}
}
