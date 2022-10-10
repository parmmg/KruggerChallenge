package com.krugger.challenge.service;

import com.krugger.challenge.entity.Permission;
import com.krugger.challenge.presentation.presenter.PermissionPresenter;
import com.krugger.challenge.service.impl.PermissionServiceImpl;
import com.krugger.challenge.service.impl.RoleServiceImpl;
import com.krugger.challenge.util.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

    @InjectMocks
    @Spy
    private PermissionService permissionService = new PermissionServiceImpl();

    private final TestData testData = new TestData();

    @Test
    public void ShouldGetPermissionToPermissionPresenter() {
        Permission permission = testData.permissionFake();
        PermissionPresenter permissionPresenter = permissionService.permissionToPermissionPresenter(permission);
        Assertions.assertThat(permissionPresenter.getName().equals(permission.getName()));
    }

}
