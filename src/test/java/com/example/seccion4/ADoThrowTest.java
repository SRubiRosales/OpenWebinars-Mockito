package com.example.seccion4;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ADoThrowTest {
    //Dependencia
    EmployeeRepository repository;
    //System under test
    EmployeeService service;

    @BeforeEach
    void setUp() {
        this.repository = mock(EmployeeRepositoryImpl.class);
        this.service = new EmployeeServiceImpl(this.repository);
    }

    @Test
    void doThrows() {
        //Lanza una excepcion simulada en un metodo void
        doThrow(new RuntimeException()).when(repository).saveAll(anyList());
        assertThrows(RuntimeException.class, () -> service.saveAll(Arrays.asList(
                new Employee(1L, "Employee1", 29),
                new Employee(2L, "Employee2", 30)
        )));
    }
}
