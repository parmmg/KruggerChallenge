package com.krugger.challenge.service;

import com.krugger.challenge.entity.Employee;
import com.krugger.challenge.entity.User;
import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.EmployeePresenter;
import com.krugger.challenge.presentation.presenter.UserPresenter;
import com.krugger.challenge.presentation.presenter.VaccinePresenter;
import com.krugger.challenge.repository.EmployeeRepository;
import com.krugger.challenge.service.impl.EmployeeServiceImpl;
import com.krugger.challenge.util.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

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
    public void shouldGetValidationExceptionWhenEmployeeExist() {
        EmployeePresenter employeePresenter = testData.employeePresenterFake();
        when(employeeRepository.findById(employeePresenter.getId())).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> employeeService.saveEmployee(employeePresenter)).isInstanceOf(ValidationException.class)
                .hasMessageContaining("Employee Not Exist");
    }

    @Test
    public void shouldSaveEmployeeAndCreateUser() {
        User user = testData.userFake();
        EmployeePresenter employeePresenter = testData.employeePresenterFake();
        employeePresenter.setId(null);
        when(userService.createUserByEmployee(user.getEmployee())).thenReturn(testData.userPresenterFake());
        Employee employee = employeeService.saveEmployee(employeePresenter);
        Assertions.assertThat(employee.getId()).isNotNull();
    }

    @Test
    public void shouldGetEmployees() {
        when(employeeRepository.findAll()).thenReturn(testData.employeesFake());
        List<EmployeePresenter> employeePresenters = employeeService.findAll();
        Assertions.assertThat(employeePresenters).isNotEmpty();
    }

    @Test
    public void shouldGetEmployeeById() {
        Employee employee = testData.employeeFake();
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        Assertions.assertThat(employee.getId().equals(employeeService.getEmployeeById(employee.getId()).getId()));
    }

}
