package com.example.seccion1;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EExceptionsTest {
    //Dependencia
    EmployeeRepository repository;
    //System under test
    EmployeeService service;

    @BeforeEach
    void setUp(){
        this.repository = mock(EmployeeRepositoryImpl.class);
        this.service = new EmployeeServiceImpl(this.repository);
    }

    @Test
    void checkException() {
        when(this.repository.findOne(any())).thenThrow(new IllegalArgumentException());
        Optional<Employee> employeeOptional = this.service.findOneOptional(1L);
        assertNotNull(employeeOptional);
        assertFalse(employeeOptional.isPresent());//Comprueba que esta vacio
    }

    @Test
    void checkException2() {
        when(this.repository.findOne(any())).thenThrow(new NoSuchElementException());//Simula el lanzamiento de la excepcion
        assertThrows(NoSuchElementException.class, ()-> this.service.findOneOptional(1L));//Verifica el comportamiento de la excepcion
    }
}
