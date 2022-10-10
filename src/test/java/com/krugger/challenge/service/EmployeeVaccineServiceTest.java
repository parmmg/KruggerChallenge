package com.krugger.challenge.service;

import com.krugger.challenge.repository.EmployeeVaccineRepository;
import com.krugger.challenge.service.impl.EmployeeVaccineServiceImpl;
import com.krugger.challenge.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeVaccineServiceTest {

    @InjectMocks
    @Spy
    private EmployeeVaccineService employeeVaccineService = new EmployeeVaccineServiceImpl();

    @Mock
    private EmployeeVaccineRepository employeeVaccineRepository;

    @Mock
    private VaccineService vaccineService;

    private final TestData testData = new TestData();

    @Test
    public void shouldSaveEmployeeVaccine() {

    }

    @Test
    public void shouldDeleteEmployeeVaccine() {

    }

    @Test
    public void shouldGetEmployeeVaccinePresenterToEmployeeVaccine() {

    }

    @Test
    public void shouldGetEmployeeVaccineToEmployeeVaccinePresenter() {

    }


}
