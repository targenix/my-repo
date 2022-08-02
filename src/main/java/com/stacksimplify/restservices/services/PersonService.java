package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.Person;
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
		public Person createPerson(Person person) {
			return personRepository.save(person);
		}
		//getPersonById
		public Optional<Person> getPersonById(Long id) {
		     Optional<Person> person=personRepository.findById(id);
		     
		     return person;
		}
		
		//updatePersonById
		
		public Person updatePersonById(Long id,Person person) {
			person.setId(id);
			return personRepository.save(person);
		}
		
		//deletePersonById
		public void deletePersonById(Long id) {
			if(personRepository.findById(id).isPresent()) {
				personRepository.deleteById(id);
			}
		}
		
		//getPersonByPersonname
		
		public Person getPersonByUsername(String username) {
			return personRepository.findByUsername(username);
			
		}

		
}
