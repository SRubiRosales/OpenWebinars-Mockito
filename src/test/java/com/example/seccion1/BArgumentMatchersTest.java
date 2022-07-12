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

public class BArgumentMatchersTest {
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
        when(this.repository.findOne(anyLong())).thenReturn(employee);//AnyLong, parametrizacion dinamica
        //When (Se invoca al metodo)
        //Se envian diferentes parametros
        Employee emp = this.service.findOne(1L);
        Employee emp2 = this.service.findOne(2L);
        Employee emp3 = this.service.findOne(3L);
        //Then (Validamos el comportamiento esperado)
        assertNotNull(emp);
        assertNotNull(emp2);
        assertNotNull(emp3);
        assertEquals("Empleado1", emp.getName());
        assertEquals("Empleado1", emp2.getName());
        assertEquals("Empleado1", emp3.getName());
    }
}
