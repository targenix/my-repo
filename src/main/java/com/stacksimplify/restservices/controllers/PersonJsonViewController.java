package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.Person;
import com.stacksimplify.restservices.entities.Views;
import com.stacksimplify.restservices.exceptions.PersonNotFoundException;
import com.stacksimplify.restservices.services.PersonService;


@RestController
@Validated
@RequestMapping(value = "/jsonview/persons")
public class PersonJsonViewController {
	
	

	//Autowire the PersonService
	@Autowired
	private PersonService personService;
	
	//getPersonById--External
	@JsonView(Views.External.class)
		@GetMapping("/external/{id}")
		public Optional<Person> getPersonById(@PathVariable("id") @Min(1) Long id){
			
			try {
				return personService.getPersonById(id);
			}catch(PersonNotFoundException ex)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
			
		}
		
		//getPersonById--Internal
				@GetMapping("/internal/{id}")
				@JsonView(Views.Internal.class)
				public Optional<Person> getPersonById2(@PathVariable("id") @Min(1) Long id){
					
					try {
						return personService.getPersonById(id);
					}catch(PersonNotFoundException ex)
					{
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
					}
					
				}

}
