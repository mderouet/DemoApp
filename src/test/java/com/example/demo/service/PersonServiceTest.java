package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPersons() {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("John Doe");
        person1.setAge(30);

        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Jane Doe");
        person2.setAge(25);

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        List<Person> persons = personService.getAllPersons();

        assertEquals(2, persons.size());
        assertEquals("John Doe", persons.get(0).getName());
        assertEquals("Jane Doe", persons.get(1).getName());
    }

    @Test
    void testGetPersonById() {
        Person person = new Person();
        person.setId(1L);
        person.setName("John Doe");
        person.setAge(30);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        Optional<Person> foundPerson = personService.getPersonById(1L);

        assertTrue(foundPerson.isPresent());
        assertEquals("John Doe", foundPerson.get().getName());
    }

    @Test
    void testCreatePerson() {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person createdPerson = personService.createPerson(person);

        assertEquals("John Doe", createdPerson.getName());
        assertEquals(30, createdPerson.getAge());
    }

    @Test
    void testUpdatePerson() {
        Person existingPerson = new Person();
        existingPerson.setId(1L);
        existingPerson.setName("John Doe");
        existingPerson.setAge(30);

        Person updatedPerson = new Person();
        updatedPerson.setName("Jane Doe");
        updatedPerson.setAge(31);

        when(personRepository.findById(1L)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Person> result = personService.updatePerson(1L, updatedPerson);

        assertTrue(result.isPresent());
        assertEquals("Jane Doe", result.get().getName());
        assertEquals(31, result.get().getAge());
    }

    @Test
    void testDeletePerson() {
        personService.deletePerson(1L);
        verify(personRepository, times(1)).deleteById(1L);
    }
}