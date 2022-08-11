package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.Person;
import com.stacksimplify.restservices.exceptions.PersonNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.PersonRepository;

@RestController
@RequestMapping(value="/hateoas/persons")
public class OrderHateoasController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get All Orders for a user
	
	
		@GetMapping("/{personid}/orders")
		public CollectionModel<Order> getAllOrders(@PathVariable Long personid) throws PersonNotFoundException
		{
			Optional<Person> personOptional = personRepository.findById(personid);
			if(!personOptional.isPresent())
				throw new PersonNotFoundException("Person Not Found");
			
			List<Order> allorders = personOptional.get().getOrders();
			return CollectionModel.of(allorders);
		}
	

}
