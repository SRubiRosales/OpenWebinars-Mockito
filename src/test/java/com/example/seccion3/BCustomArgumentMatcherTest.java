package com.example.seccion3;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BCustomArgumentMatcherTest {
    //Dependencia
    EmployeeRepository repository;
    //System under test
    EmployeeService service;

    @BeforeEach
    void setUp(){
        this.repository = mock(EmployeeRepository.class);
        this.service = new EmployeeServiceImpl(this.repository);
    }

    @Test
    void saveAllTest(){
        //Service
        service.saveAll(Arrays.asList(
                new Employee(1L, "empleado1", 30),
                new Employee(2L, "empleado2", 31),
                new Employee(3L, "empleado3", 32)
        ));
        //Verify, valida que se ha invocado una vez el metodo saveAll y se ha enviado una lista de 3 elementos
        verify(repository).saveAll(argThat(list -> list.size() == 3));
    }
}
