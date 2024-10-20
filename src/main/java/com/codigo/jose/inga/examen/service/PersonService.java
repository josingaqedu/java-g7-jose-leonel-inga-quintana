package com.codigo.jose.inga.examen.service;

import com.codigo.jose.inga.examen.entity.PersonEntity;

import java.util.List;

public interface PersonService {
    PersonEntity createPerson(PersonEntity personEntity);
    List<PersonEntity> getAllPersons();
    PersonEntity getPersonById(Long id);
    PersonEntity getPersonByDni(String dni);
    PersonEntity updatePerson(Long id, PersonEntity personEntity);
    void deletePerson(Long id);
}
