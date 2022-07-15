package com.example.seccion4;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import com.example.demo.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class CDoAnswerTest {
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
    void answer() {
        doAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            Person person = (Person) args[0];
            String email = (String) args[1];
            person.setEmail(email);
            return null;
        }).when(repository).updateEmail(any(Person.class), any(String.class));
        Person person = new Person(null, "email@mail.com", null);
        Person personDB = service.updateEmail(person,"nuevoemail@mail.com");
        assertNotNull(personDB);
        assertEquals("nuevoemail@mail.com", personDB.getEmail());
    }
}
