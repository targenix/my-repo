package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Person;
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
	
	public Person createPerson(@RequestBody Person person)
	{
		return personService.createPerson(person);
	}
	
	//getPersonById
	@GetMapping("/persons/{id}")
	public Optional<Person> getPersonBy(@PathVariable("id")Long id){
		return personService.getPersonById(id);
	}
	
	//updatePersonById
	@PutMapping("/persons/{id}")
	public Person updatePersonById(@PathVariable("id") Long id,@RequestBody Person person) {
		return personService.updatePersonById(id,person);
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
