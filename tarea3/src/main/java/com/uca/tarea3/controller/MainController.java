package com.uca.tarea3.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/ingresar")
	public ModelAndView index() {
		
		ModelAndView mav = new ModelAndView();

		
		mav.setViewName("commons/index");
		return mav;
		
	}
	@RequestMapping("/params")
	public ModelAndView params(@RequestParam String name,@RequestParam String lastname, @RequestParam String dateOfBirth,
	@RequestParam String placeOfBirth, @RequestParam String school, @RequestParam String homePhone, @RequestParam String mobilePhone) {
		
		ModelAndView mav = new ModelAndView();
	
		List<String> errors = validateErrors( name, lastname,dateOfBirth, placeOfBirth, school,  homePhone, mobilePhone);
		if(!errors.isEmpty()) {
			mav.addObject("errors",errors);
			mav.setViewName("commons/bad");
			
		}else {
			mav.addObject("user",name);
			mav.addObject("password",lastname);
			mav.setViewName("commons/params");
			
		}
		
		return mav;
		
	}
	
	List<String> validateErrors(String name,String lastname, String dateOfBirth,
			String placeOfBirth,String school, String homePhone, String mobilePhone){
		List<String> errors = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		  
		LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
		LocalDate valDate = LocalDate.parse("2003-01-01", formatter);
		
		if(birthDate.compareTo(valDate)<0) {
			errors.add("La fecha debe ser mayor al 1 de enero del 2003");
		}
		
		
		
		if(name.isEmpty() || name == null) {
			errors.add("el nombre no puede estar vacio");
		}
		if(lastname.isEmpty() || lastname == null) {
			errors.add("el apellido no puede estar vacio");
		}
		if(placeOfBirth.isEmpty() || placeOfBirth== null) {
			errors.add("el lugar de nacimiento no puede estar vacio");
		}
		
		if(school.isEmpty() || school== null) {
			errors.add("el colegio no puede estar vacio");
		}
		
		
		if(name.length() > 25  ) {
			errors.add("El Nombre no puede ser mayor de 25 caracteres");
		}
		if(lastname.length() > 25 ) {
			errors.add("El Apellido no puede ser mayor de 25 caracteres");
		}
		if(placeOfBirth.length() > 25  ) {
			errors.add("El lugar de nacimiento no puede ser mayor de 25 caracteres");
		}
		if(school.length() > 100  ) {
			errors.add("El colegio/instituto no puede ser mayor de 100 caracteres");
		}
		 
		if(homePhone.length() != 8) {
			errors.add("El telefno fijo debe tener 8 numeros");
		}
		if(mobilePhone.length() != 8) {
			errors.add("El telefno movil debe tener 8 numeros");
		}	

		return errors;
	
	}

		
}
	