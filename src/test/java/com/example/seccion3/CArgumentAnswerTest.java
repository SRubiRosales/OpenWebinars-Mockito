package com.example.seccion3;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import com.example.demo.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CArgumentAnswerTest {
    //Dependencia
    PersonRepository repository;
    //System under test
    PersonService service;

    @BeforeEach
    void setUp(){
        this.repository = mock(PersonRepository.class);
        this.service = new PersonServiceImpl(this.repository);
    }

    @Test
    void name() {
        LocalDate defaultBirth = LocalDate.of(1970, 1, 1);//Fecha por defecto
        when(repository.save(any())).thenAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            Person person = (Person) args[0];//Extrae el argumento
            LocalDate birth = person.getBirthday() != null ? person.getBirthday() : defaultBirth;
            return new Person(UUID.randomUUID(), person.getName(), birth);
        });

        Person personResult = service.save(new Person(null, "Prueba", null));
        assertNotNull(personResult);
        assertEquals("Prueba", personResult.getName());
        assertEquals(defaultBirth, personResult.getBirthday());
    }
}
