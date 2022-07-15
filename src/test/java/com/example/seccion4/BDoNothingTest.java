package com.example.seccion4;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class BDoNothingTest {
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
    @DisplayName("test")
    void test() {
        doNothing().when(repository).deleteAll();
        //Al invocar el metodo no se haria nada
        service.deleteAll();
        service.deleteAll();
        verify(repository, times(2)).deleteAll();
    }
}
