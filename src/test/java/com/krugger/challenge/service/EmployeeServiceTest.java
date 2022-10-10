package com.krugger.challenge.service;

import com.krugger.challenge.repository.EmployeeRepository;
import com.krugger.challenge.service.impl.EmployeeServiceImpl;
import com.krugger.challenge.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest  {

    @InjectMocks
    @Spy
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeVaccineService employeeVaccineService;

    @Mock
    private UserService userService;

    private final TestData testData = new TestData();

    @Test
    public void shouldSaveEmployee() {

    }

    @Test
    public void shouldGetEmployees() {

    }

    @Test
    public void shouldGetEmployeeById() {

    }

    @Test
    public void shouldGetEmployeeToEmployeePresenter() {

    }

    @Test
    public void shouldDeleteEmployeeById() {

    }

    @Test
    public void shouldNoDeleteEmployeeById() {

    }

    @Test
    public void shouldGetSearchEmployeesByFilters() {

    }

    @Test
    public void shouldGetEmployeesByStatus() {

    }

    @Test
    public void shouldGetEmployeesByVaccineDate() {

    }

}
