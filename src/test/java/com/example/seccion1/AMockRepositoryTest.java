package com.example.seccion1;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AMockRepositoryTest {//Por convencion el nombre de la clase termina en "Test"
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
    void findOneTest(){
        //Given (Preparacion estado inicial)
        Employee employee = new Employee(1L, "Empleado1", 40);
        when(this.repository.findOne(1L)).thenReturn(employee);
        //When (Se invoca al metodo)
        Employee emp = this.service.findOne(1L);
        //Then (Validamos el comportamiento esperado)
        assertNotNull(emp);
        assertEquals("Empleado1", emp.getName());
    }
}
