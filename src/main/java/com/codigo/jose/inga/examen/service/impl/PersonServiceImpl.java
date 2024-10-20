package com.codigo.jose.inga.examen.service.impl;

import com.codigo.jose.inga.examen.entity.PersonEntity;
import com.codigo.jose.inga.examen.exception.PersonAlreadyExistsException;
import com.codigo.jose.inga.examen.exception.PersonNotFoundException;
import com.codigo.jose.inga.examen.repository.PersonRepository;
import com.codigo.jose.inga.examen.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonEntity createPerson(PersonEntity personEntity) {
        // Verify if exists a person with this DNI
        PersonEntity person = personRepository.findByDni(personEntity.getDni()).orElse(null);

        // If the person exists, throw an exception
        if (person != null) throw new PersonAlreadyExistsException("Person with this DNI already exists");

        // Update the status of the person
        personEntity.setStatus("ACTIVE");

        // Update the status of the orders
        if (personEntity.getOrders() != null) {
            personEntity.getOrders().forEach(order -> order.setStatus("PENDING"));
        }

        // Create the person
        return personRepository.save(personEntity);
    }

    @Override
    public List<PersonEntity> getAllPersons() {
        return personRepository.findAllByStatus("ACTIVE");
    }

    @Override
    public PersonEntity getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person Not Found"));
    }

    @Override
    public PersonEntity getPersonByDni(String dni) {
        return personRepository.findByDni(dni).orElseThrow(() -> new PersonNotFoundException("Person with this DNI not found"));
    }

    @Override
    public PersonEntity updatePerson(Long id, PersonEntity personEntity) {
        // Verify if the person exists to update
        PersonEntity person = getPersonById(id);

        // Verify if the DNI is already in use by another person
        personRepository.findByDni(personEntity.getDni()).ifPresent(existingPerson -> {
            if (!existingPerson.getId().equals(id)) {
                throw new PersonAlreadyExistsException("Person with this DNI already exists");
            }
        });

        person.setName(personEntity.getName());
        person.setLastName(personEntity.getLastName());
        person.setDni(personEntity.getDni());
        person.setAddress(personEntity.getAddress());

        // Assign status to the orders
        if (personEntity.getOrders() != null) {
            personEntity.getOrders().forEach(order -> {
                if (order.getId() == null) {
                    // New order
                    order.setStatus("PENDING");
                } else {
                    // Old order
                    order.setStatus("PROCESSED");
                }

                // Assign the person to the order
                order.setPerson(person);
            });
        }

        // Assign the orders to the person
        person.setOrders(personEntity.getOrders());

        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        // Verify if the person exists to update
        PersonEntity person = getPersonById(id);

        // Update the status of the person
        person.setStatus("INACTIVE");

        // Update the status of the orders
        person.getOrders().forEach(order -> order.setStatus("DELETED"));

        personRepository.save(person);
    }
}
