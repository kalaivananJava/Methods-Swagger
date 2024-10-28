package com.kgisl.sb101.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.sb101.entity.Person;
import com.kgisl.sb101.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
   @Autowired
    PersonRepository pr;
    @Override
    public List<Person> getAllPersons(){
        return pr.findAll();
    }
    @Override
    public Person getPersonById(int id) {
    return pr.findById(id).orElse(null);
    
    }
    @Override
    public Person createPerson(Person person) {
        return pr.save(person);

    }
    @Override
    public void deletePerson(int id) {
        pr.deleteById(id);
    }
 

  
}
