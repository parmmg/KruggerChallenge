package com.krugger.challenge.service.impl;

import com.krugger.challenge.entity.Role;
import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.PermissionPresenter;
import com.krugger.challenge.presentation.presenter.RolePresenter;
import com.krugger.challenge.repository.RoleRepository;
import com.krugger.challenge.service.PermissionService;
import com.krugger.challenge.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleRepository roleRepository;

    public RolePresenter roleToRolePresenter(Role role) {
        if (role == null) {
            return null;
        }
        List<PermissionPresenter> permissionPresenters = new ArrayList<>();
        role.getPermissions().forEach(permission -> permissionPresenters.add(permissionService.permissionToPermissionPresenter(permission)));
        return RolePresenter.builder()
                .id(role.getId())
                .name(role.getName())
                .permissionPresenters(permissionPresenters)
                .build();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new ValidationException("Role Not Exist"));
    }

}
