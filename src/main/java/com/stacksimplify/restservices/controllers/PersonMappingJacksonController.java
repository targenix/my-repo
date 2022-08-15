package com.stacksimplify.restservices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.Person;
import com.stacksimplify.restservices.exceptions.PersonNotFoundException;
import com.stacksimplify.restservices.services.PersonService;

@RestController
@RequestMapping(value = "/jacksonfilter/persons")
@Validated
public class PersonMappingJacksonController {
	
	//Autowire the PersonService
	@Autowired
	private PersonService personService;
	
	//getPersonById--fields with Hashset
		@GetMapping("/{id}")
		public MappingJacksonValue getPersonById(@PathVariable("id") @Min(1) Long id){
			
			try {
				Optional<Person> personOptional = personService.getPersonById(id);
				Person person = personOptional.get();
				Set<String> fields = new HashSet<String>();
				fields.add("id");
				fields.add("username");
				fields.add("ssn");
				fields.add("orders");
				FilterProvider filterProvider = new SimpleFilterProvider().addFilter("personFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
				MappingJacksonValue mapper = new MappingJacksonValue(person);
				
				mapper.setFilters(filterProvider);
				return mapper;
				
			}catch(PersonNotFoundException ex)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
			
		}
		//getPersonById--fields with @Requestparam
				@GetMapping("/params/{id}")
				public MappingJacksonValue getPersonById2(@PathVariable("id") @Min(1) Long id,@RequestParam Set<String> fields){
					
					try {
						Optional<Person> personOptional = personService.getPersonById(id);
						Person person = personOptional.get();
						
						FilterProvider filterProvider = new SimpleFilterProvider().addFilter("personFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
						MappingJacksonValue mapper = new MappingJacksonValue(person);
						
						mapper.setFilters(filterProvider);
						return mapper;
						
					}catch(PersonNotFoundException ex)
					{
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
					}
					
				}

}
