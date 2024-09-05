package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
    }

    @Test
    void testCreatePerson() throws Exception {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        mockMvc.perform(post("/api/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void testGetAllPersons() throws Exception {
        Person person1 = new Person();
        person1.setName("John Doe");
        person1.setAge(30);
        personRepository.save(person1);

        Person person2 = new Person();
        person2.setName("Jane Doe");
        person2.setAge(25);
        personRepository.save(person2);

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    void testGetPersonById() throws Exception {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        person = personRepository.save(person);

        mockMvc.perform(get("/api/persons/" + person.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        person = personRepository.save(person);

        Person updatedPerson = new Person();
        updatedPerson.setName("Jane Doe");
        updatedPerson.setAge(31);

        mockMvc.perform(put("/api/persons/" + person.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPerson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.age").value(31));
    }

    @Test
    void testDeletePerson() throws Exception {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        person = personRepository.save(person);

        mockMvc.perform(delete("/api/persons/" + person.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/persons/" + person.getId()))
                .andExpect(status().isNotFound());
    }
}