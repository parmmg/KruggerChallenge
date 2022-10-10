package com.krugger.challenge.service;


import com.krugger.challenge.entity.Role;
import com.krugger.challenge.presentation.presenter.RolePresenter;

public interface RoleService {

    RolePresenter roleToRolePresenter(Role role);

    Role findByName(String name);

}
