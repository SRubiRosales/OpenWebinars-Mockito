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

public class BDoCallRealMethodTest {
    //Dependencia
    EmployeeRepository repository;
    //System under test
    EmployeeService service;

    @BeforeEach
    void setUp(){
        this.repository = mock(EmployeeRepositoryImpl.class);//Implementacion
        this.service = new EmployeeServiceImpl(this.repository);
    }

    @Test
    void findOneTest() {
        //Given
        Employee employee1 = new Employee(1L, "Prueba", 25);
        //when(this.repository.findOne(1L)).thenReturn(employee1);//No invoca el metodo real
        doCallRealMethod().when(repository).findOne(1L);//Invoca al metodo real desde el mock
        //When
        Employee employee2 = service.findOne(1L);

        //Then
        assertEquals("Prueba", employee2.getName());
        verify(repository).findOne(1L);//Invoca al metodo real
    }
}
