package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.Person;
import com.stacksimplify.restservices.exceptions.PersonExistsException;
import com.stacksimplify.restservices.exceptions.PersonNotFoundException;
import com.stacksimplify.restservices.services.PersonService;

//Controller
@RestController
public class PersonController {
	
	//Autowire the PersonService
	@Autowired
	private PersonService personService;
	
	//getAllUsers Method
	
	@GetMapping("/persons")
	
	public List<Person> getAllPersons(){
		
		return personService.getAllPersons();
	}
	
	//Create Person
	//@RequestBody Annotation
	//@PostMapping Annotation
	@PostMapping("/persons")
	
	public ResponseEntity<Void> createPerson(@RequestBody Person person,UriComponentsBuilder builder)
	{ try {
		 personService.createPerson(person);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(builder.path("/persons/{id}").buildAndExpand(person.getId()).toUri());
		 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	} catch (PersonExistsException ex)
	{
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
	}
	}
	
	//getPersonById
	@GetMapping("/persons/{id}")
	public Optional<Person> getPersonBy(@PathVariable("id")Long id){
		
		try {
			return personService.getPersonById(id);
		}catch(PersonNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	//updatePersonById
	@PutMapping("/persons/{id}")
	public Person updatePersonById(@PathVariable("id") Long id,@RequestBody Person person) {
		
		try {
			return personService.updatePersonById(id,person);
		}
		catch (PersonNotFoundException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
	}
	
	//deletePersonById
	@DeleteMapping("/persons/{id}")
	public void deletePersonById(@PathVariable("id")Long id) {
		personService.deletePersonById(id);
	}
	
	//getPersonByPersonname
	@GetMapping("/persons/byusername/{username}")
	public Person getPersonByUsername(@PathVariable("username")String username) {
		return personService.getPersonByUsername(username);
	}

}
