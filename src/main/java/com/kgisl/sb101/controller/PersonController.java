package com.kgisl.sb101.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.sb101.entity.Person;
import com.kgisl.sb101.service.PersonService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RequestMapping("/person")
@RestController
public class PersonController {
   @Autowired
   private PersonService personService;
  
   
  
   public PersonController(PersonService personService) {
      this.personService = personService;
   }



   @GetMapping
   public ResponseEntity<List<Person>> getAllPersons(){
       List<Person> persons= personService.getAllPersons();
       return new ResponseEntity<>(persons,HttpStatus.OK);
   }
  
   @GetMapping("/{id}")
   public ResponseEntity<Person> getPersonById(@PathVariable int id){
      Person person = personService.getPersonById(id);
      return new ResponseEntity<>(person,person!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
   }
    
   // @GetMapping("/{name}")
   // public ResponseEntity<Person> getPersonByName(@PathVariable String name){
       
   //    Person person = personService.getPersonByName(name);
   //    return new ResponseEntity<>(person,person!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
   // }
  
   @PostMapping("/person")
public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person) {
    // Check if the input person is null (although @Valid should handle this)
    if (person == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
    }

    // Create the person using the service
    Person createdPerson = personService.createPerson(person);
    
    // Return the created person with a 201 Created status
    return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
}


   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> deletePerson(@PathVariable int id){
      if (personService.getPersonById(id) == null) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     personService.deletePerson(id);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

}
