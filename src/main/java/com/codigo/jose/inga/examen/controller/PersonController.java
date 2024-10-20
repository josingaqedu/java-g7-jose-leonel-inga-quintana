package com.codigo.jose.inga.examen.controller;

import com.codigo.jose.inga.examen.entity.PersonEntity;
import com.codigo.jose.inga.examen.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons/v1")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity personEntity){
        PersonEntity person = personService.createPerson(personEntity);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonEntity>> getAllPersons(){
        List<PersonEntity> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable Long id){
        PersonEntity person = personService.getPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PersonEntity> getPersonByDni(@PathVariable String dni){
        PersonEntity person = personService.getPersonByDni(dni);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable Long id, @RequestBody PersonEntity personEntity){
        PersonEntity person = personService.updatePerson(id, personEntity);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
