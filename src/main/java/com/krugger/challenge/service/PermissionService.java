package com.krugger.challenge.service;


import com.krugger.challenge.entity.Permission;
import com.krugger.challenge.presentation.presenter.PermissionPresenter;

public interface PermissionService {

    public PermissionPresenter permissionToPermissionPresenter(Permission permission);

}
