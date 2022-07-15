package com.example.seccion3;

import com.example.demo.domain.Employee;
import com.example.demo.service.IRPFCalculator;
import com.example.demo.service.IVACalculator;
import com.example.demo.service.SalaryCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class DInvocationOrderTest {
    //Dependencias
    private IRPFCalculator irpfCalculator;
    private IVACalculator ivaCalculator;
    //System under test
    private SalaryCalculatorService service;

    @BeforeEach
    void setUp() {
        this.irpfCalculator = mock(IRPFCalculator.class);
        this.ivaCalculator = mock(IVACalculator.class);
        this.service = new SalaryCalculatorService(irpfCalculator, ivaCalculator);
    }

    @Test
    void salaryCalculator() {
        when(irpfCalculator.calculateIRPF(32000d)).thenReturn(4800d);
        when(ivaCalculator.calculateIVA(36800d)).thenReturn(7728d);
        Employee employee = new Employee(1L, "Empleado", 32);
        double salary = this.service.calculateSalary(employee);
        //Verifica el orden en que se ejecutan los mocks
        InOrder order = inOrder(irpfCalculator, ivaCalculator);
        order.verify(irpfCalculator).calculateIRPF(32000.0);
        order.verify(ivaCalculator).calculateIVA(36800d);
    }
}
