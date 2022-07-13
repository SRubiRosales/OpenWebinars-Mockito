package com.example.seccion2;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AMockRepositoryTest {
    //Dependencia
    EmployeeRepository repository;
    //System under test
    EmployeeService service;

    @BeforeEach
    void setUp(){
        //Utiliza una implementacion en lugar de una interfaz. Invoca al metodo real
        this.repository = spy(EmployeeRepositoryImpl.class);
        this.service = new EmployeeServiceImpl(this.repository);
    }

    @Test
    void findOneTest() {
        Employee employee1 = service.findOne(1L);
        verify(repository).findOne(1L);//Invoca al metodo real
    }

    @Test
    void findOneTest2() {
        //Given
        Employee employee1 = new Employee(1L, "Prueba", 25);
        //when(repository.findOne(1L)).thenReturn(employee1);//Ejecuta el metodo real
        doReturn(employee1).when(repository).findOne(1L);//No ejecuta el metodo real
        //When
        Employee employee2 = service.findOne(1L);

        //Then
        assertEquals("Prueba", employee2.getName());
        verify(repository).findOne(1L);//Invoca al metodo real
    }
}
