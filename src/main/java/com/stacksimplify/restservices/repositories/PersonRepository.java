package com.stacksimplify.restservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.entities.Person;

//Repository
	@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

		//List<Person> findAll();
		Person findByUsername(String username);

}
