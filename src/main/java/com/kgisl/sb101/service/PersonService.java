package com.kgisl.sb101.service;

import java.util.List;

import com.kgisl.sb101.entity.Person;

public interface PersonService   {
  
   public List<Person> getAllPersons();
//    public Person getPersonByName(String name);
   public Person getPersonById(int id);
   public Person createPerson(Person person);
   public void deletePerson(int id);
}
