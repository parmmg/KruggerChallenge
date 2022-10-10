package com.krugger.challenge.service.impl;

import com.krugger.challenge.entity.Permission;
import com.krugger.challenge.presentation.presenter.PermissionPresenter;
import com.krugger.challenge.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Override
    public PermissionPresenter permissionToPermissionPresenter(Permission permission) {
        return PermissionPresenter.builder()
                .id(permission.getId())
                .domainAction(permission.getDomainAction())
                .name(permission.getName())
                .build();
    }

}
