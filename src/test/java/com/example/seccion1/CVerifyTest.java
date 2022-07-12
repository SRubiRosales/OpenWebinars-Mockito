package com.example.seccion1;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CVerifyTest {
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
        Employee emp2 = this.service.findOne(1L);
        Employee emp3 = this.service.findOne(3L);
        //Then (Validamos el comportamiento esperado)
        assertNotNull(emp);
        assertNotNull(emp2);
        assertNotNull(emp3);
        assertEquals("Empleado1", emp.getName());
        assertEquals("Empleado1", emp2.getName());
        assertEquals("Empleado1", emp3.getName());

        //verify(repository).findOne(1L);//Verifica se haya ejecutado este metodo con este parametro UNA VEZ
        verify(repository, times(2)).findOne(1L);//Verifica se haya ejecutado este metodo con este parametro el numero de veces indicado
        verify(repository, atMostOnce()).findOne(2L);//Se ejecuta como maximo una vez
        verify(repository, atLeastOnce()).findOne(3L);//Se ejecuta al menos una vez
        verify(repository, never()).findOne(7L);//Nunca se ejecuta
        verify(repository, times(3)).findOne(any());//En total se ejecuta 3 veces
    }
}
