package com.example.seccion1;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)//IMPORTANTE!!! Inicializa el mock
public class DInjectMocksTest {
    //Dependencia
    @Mock
    private EmployeeRepository repository;
    //Class under test
    //System under test (sut)
    @InjectMocks//Inyeccion de dependencia
    private EmployeeServiceImpl service;

    //Si se omite la anotacion ExtendWith, utilizar metodo setUp
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findOneTest(){
        //Given (Preparacion estado inicial)
        Employee employee = new Employee(1L, "Empleado1", 40);
        when(this.repository.findOne(anyLong())).thenReturn(employee);
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
