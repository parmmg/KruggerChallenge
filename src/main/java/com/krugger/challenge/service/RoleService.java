package com.krugger.challenge.service;


import com.krugger.challenge.entity.Role;
import com.krugger.challenge.presentation.presenter.RolePresenter;

import java.util.UUID;

public interface RoleService {

    RolePresenter roleToRolePresenter(Role role);

    Role findByName(String name);

    Role findById(UUID id);

}
