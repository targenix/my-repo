package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Person;
import com.stacksimplify.restservices.exceptions.PersonExistsException;
import com.stacksimplify.restservices.exceptions.PersonNotFoundException;
import com.stacksimplify.restservices.repositories.PersonRepository;


//Service
@Service
public class PersonService {
	
	//AutoWire the person repository
	@Autowired
	private PersonRepository personRepository;
		
		//getAllUsers Method
		public List<Person>getAllPersons()
		{
		    return personRepository.findAll();
		
		}
		
		//CreatePerson Method
		public Person createPerson(Person person) throws PersonExistsException {
			
			//if person exist using username
			Person existingPerson = personRepository.findByUsername(person.getUsername());
			
			//if not exists throw PersonExistsException
			if(existingPerson!=null) {
				throw new PersonExistsException("User already exists in repository");
			}
			
			return personRepository.save(person);
		}
		//getPersonById
		public Optional<Person> getPersonById(Long id)throws PersonNotFoundException {
		     Optional<Person> person=personRepository.findById(id);
		     if(!person.isPresent()) {
		    	 throw new PersonNotFoundException("User not found in Person Repository");
		     }
		     
		     return person;
		}
		
		//updatePersonById
		
		public Person updatePersonById(Long id,Person person) throws PersonNotFoundException{
			
			Optional<Person> optionalPerson=personRepository.findById(id);
		     if(!optionalPerson.isPresent()) {
		    	 throw new PersonNotFoundException("User not found in Person Repository,provide the correct Person id");
		     }
		     
			person.setId(id);
			return personRepository.save(person);
		}
		
		//deletePersonById
		public void deletePersonById(Long id) {
			
			Optional<Person> optionalPerson=personRepository.findById(id);
			
		     if(!optionalPerson.isPresent()) {
		    	 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in Person Repository,provide the correct Person id");
		     }
		     
			personRepository.deleteById(id);
		}
		
		//getPersonByPersonname
		
		public Person getPersonByUsername(String username) {
			return personRepository.findByUsername(username);
			
		}

		
}
