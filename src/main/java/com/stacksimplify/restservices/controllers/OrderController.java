package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.Person;
import com.stacksimplify.restservices.exceptions.PersonNameNotFoundException;
import com.stacksimplify.restservices.exceptions.PersonNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.PersonRepository;

@RestController
@RequestMapping(value="/persons")
public class OrderController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get All Orders for a user
	
	
	@GetMapping("/{personid}/orders")
	public List<Order> getAllOrders(@PathVariable Long personid) throws PersonNotFoundException
	{
		Optional<Person> personOptional = personRepository.findById(personid);
		if(!personOptional.isPresent())
			throw new PersonNotFoundException("Person Not Found");
		
		return personOptional.get().getOrders();
	}
	
	//Create Order
	
	@PostMapping("{personid}/orders")
	public Order createOrder(@PathVariable Long personid, @RequestBody Order order) throws PersonNotFoundException {
		Optional<Person> personOptional = personRepository.findById(personid);
		if(!personOptional.isPresent())
			throw new PersonNotFoundException("Person Not Found");
		
		Person person = personOptional.get();
		order.setPerson(person);
		return orderRepository.save(order);
	}
	
	
}
