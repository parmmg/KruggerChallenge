package com.krugger.challenge.service;

import com.krugger.challenge.entity.Employee;
import com.krugger.challenge.entity.User;
import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.UserPresenter;
import com.krugger.challenge.repository.UserRepository;
import com.krugger.challenge.service.impl.UserServiceImpl;
import com.krugger.challenge.util.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    @Spy
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    private final TestData testData = new TestData();

    @Test
    public void shouldGetUsers(){
        List<User> users = new ArrayList<>();
        users.add(testData.userFake());
        when(userRepository.findAll()).thenReturn(users);
        List<UserPresenter> userPresenters = userService.getUsers();
        Assertions.assertThat(userPresenters).isNotEmpty();
    }

    @Test
    public void shouldCreateUserByEmployee() {
        User user = testData.userFake();
        Employee employee = user.getEmployee();
        when(passwordEncoder.encode(employee.getDni())).thenReturn(anyString());
        when(roleService.findByName("Employee")).thenReturn(testData.roleFake());
        when(roleService.roleToRolePresenter(testData.roleFake())).thenReturn(testData.rolePresenterFake());
        UserPresenter userPresenter = userService.createUserByEmployee(employee);
        Assertions.assertThat(userPresenter).isNull();
    }

    @Test
    public void shouldGetValidationExceptionWhenEmployeeExist() {
        User user = testData.userFake();
        Employee employee = user.getEmployee();
        when(userRepository.findById(employee.getId())).thenReturn(Optional.of(testData.userFake()));
        lenient().when(passwordEncoder.encode(employee.getDni())).thenReturn(anyString());
        Assertions.assertThatThrownBy(() -> userService.createUserByEmployee(employee)).isInstanceOf(ValidationException.class)
                .hasMessageContaining("User already exist");
    }
}