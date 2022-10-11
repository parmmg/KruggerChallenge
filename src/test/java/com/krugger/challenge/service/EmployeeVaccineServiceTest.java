package com.krugger.challenge.service;

import com.krugger.challenge.entity.EmployeeVaccine;
import com.krugger.challenge.entity.Role;
import com.krugger.challenge.presentation.presenter.EmployeeVaccinePresenter;
import com.krugger.challenge.presentation.presenter.RolePresenter;
import com.krugger.challenge.repository.EmployeeVaccineRepository;
import com.krugger.challenge.service.impl.EmployeeVaccineServiceImpl;
import com.krugger.challenge.util.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

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
        EmployeeVaccinePresenter employeeVaccinePresenter = testData.employeeVaccinePresenterFake();
        EmployeeVaccine employeeVaccine = employeeVaccineService.saveEmployeeVaccine(employeeVaccinePresenter);
        Assertions.assertThat(employeeVaccine.getActive()==true);
    }

    @Test
    public void shouldDeleteEmployeeVaccine() {
        EmployeeVaccine employeeVaccine = testData.employeeVaccineFake();
        employeeVaccineService.delete(employeeVaccine.getId());
        Assertions.assertThat(employeeVaccine.getActive()==false);
    }

    @Test
    public void shouldGetEmployeeVaccinePresenterToEmployeeVaccine() {
        EmployeeVaccine employeeVaccine = testData.employeeVaccineFake();
        when(vaccineService.vaccineToVaccinePresenter(employeeVaccine.getVaccine())).thenReturn(testData.vaccinePresenterFake());
        EmployeeVaccinePresenter employeeVaccinePresenter = employeeVaccineService.employeeVaccineToEmployeeVaccinePresenter(employeeVaccine);
        Assertions.assertThat(employeeVaccinePresenter.getVaccinePresenter().getName().equals(employeeVaccine.getVaccine().getName()));
    }

    @Test
    public void shouldGetEmployeeVaccineToEmployeeVaccinePresenter() {
        EmployeeVaccinePresenter employeeVaccinePresenter = testData.employeeVaccinePresenterFake();
        when(vaccineService.findById(employeeVaccinePresenter.getVaccinePresenter().getId())).thenReturn(testData.vaccineFake());
        EmployeeVaccine employeeVaccine = employeeVaccineService.employeeVaccinePresenterToEmployeeVaccine(employeeVaccinePresenter);
        Assertions.assertThat(employeeVaccinePresenter.getVaccinePresenter().getName().equals(employeeVaccine.getVaccine().getName()));
    }


}
