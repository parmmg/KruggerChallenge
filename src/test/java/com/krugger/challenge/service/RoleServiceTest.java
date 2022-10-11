package com.krugger.challenge.service;

import com.krugger.challenge.entity.Role;
import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.RolePresenter;
import com.krugger.challenge.repository.RoleRepository;
import com.krugger.challenge.service.impl.RoleServiceImpl;
import com.krugger.challenge.util.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @InjectMocks
    @Spy
    private RoleService roleService = new RoleServiceImpl();

    @Mock
    private RoleRepository roleRepository;

    private final TestData testData = new TestData();

    @Test
    public void shouldRoleToRolePresenter() {
        Role role = testData.roleFake();
        RolePresenter rolePresenter = roleService.roleToRolePresenter(role);
        Assertions.assertThat(rolePresenter.getName().equals(role.getName()));
    }

    @Test
    public void shouldFindRoleByName() {
        when (roleRepository.findByName("name")).thenReturn(Optional.of(testData.roleFake()));
        Role role = roleService.findByName("name");
        Assertions.assertThat(role).isNotNull();
    }

    @Test
    public void shouldGetValidationExceptionWhenRoleNoExist() {
        Assertions.assertThatThrownBy(() -> roleService.findByName("")).isInstanceOf(ValidationException.class)
                .hasMessageContaining("Role Not Exist");
    }

}
