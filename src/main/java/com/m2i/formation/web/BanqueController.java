package com.m2i.formation.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.m2i.formation.entites.Compte;
import com.m2i.formation.entites.Operation;
import com.m2i.formation.service.IbanqueMetier;

@Controller
public class BanqueController {

	@Autowired
	IbanqueMetier banqueMetier;

	@RequestMapping(value = "/operations")
	public String index() {

		return "comptes";
	}

	@RequestMapping(value = "/consulterCompte")
	public String consulterCompte(String codeCompte, Model model ,
			@RequestParam(name="page", defaultValue="0")int page , 
			@RequestParam(name="size" , defaultValue="4")int size) {
		
		model.addAttribute("codeCompte", codeCompte) ; 

		try {

			Compte cp = banqueMetier.consulterCompte(codeCompte);
			
			Page<Operation> lesOperations = banqueMetier.listOperation(codeCompte, page, size) ; 
			int[]pages = new int[lesOperations.getTotalPages()] ; 
			model.addAttribute("pages", pages) ;
			model.addAttribute("listOp", lesOperations.getContent()) ; 
			
			model.addAttribute("compte", cp);

		} catch (Exception e) {
			model.addAttribute("exception", e) ;

		}

		return "comptes";
	}

	
	@RequestMapping(value = "/saveOperation" , method=RequestMethod.POST )
	public String saveOperation(String codeCompte, Model model, String codeCompte2 , String typeOperation , double montant) {
		
		try {
			if(typeOperation.equals("VERS")) {
				banqueMetier.verser(codeCompte, montant);
			}
			else if(typeOperation.equals("RETR"))
			{
				banqueMetier.retirer(codeCompte, montant);
			}
			else {
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
			
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		
		
		

		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
	
	
	
}
