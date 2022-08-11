package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.Person;
import com.stacksimplify.restservices.exceptions.PersonNotFoundException;
import com.stacksimplify.restservices.repositories.PersonRepository;
import com.stacksimplify.restservices.services.PersonService;

@RestController
@RequestMapping(value="/hateoas/persons")
@Validated
public class PersonHateoasController {
	
	

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	
	//getPersonById
		@GetMapping("/{id}")
		public EntityModel<Person> getPersonById(@PathVariable("id") @Min(1) Long id){
			
			try {
				Optional<Person> personOptional = personService.getPersonById(id);
				Person person = personOptional.get();
				Long personid=person.getId();
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(personid).withSelfRel();
				person.add(selflink);
			    //EntityModel<Person> finalResource = new EntityModel<Person>(person);
				return EntityModel.of(person);
			
				
			}catch(PersonNotFoundException ex)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
			
		}
	
	//getAllUsers Method
	
		@GetMapping
		
		public CollectionModel<Person> getAllPersons() throws PersonNotFoundException{
			
			List<Person> allpersons = personService.getAllPersons();
			for(Person person : allpersons) {
				//Self Link
				Long personid =person.getId();
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(personid).withSelfRel();
				person.add(selflink);
				
				//Relationship Link with getAllOrders
				CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(personid);
				Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
				person.add(orderslink);
			}
			//Self link for getAllPersons
			Link selflinkgetAllPersons = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
			
			return CollectionModel.of(allpersons,selflinkgetAllPersons);
		}

}
