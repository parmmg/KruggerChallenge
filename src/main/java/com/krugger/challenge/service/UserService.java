package com.krugger.challenge.service;


import com.krugger.challenge.entity.User;
import com.krugger.challenge.presentation.presenter.UserPresenter;
import com.krugger.challenge.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserPresenter createUserByEmployee(Employee employee);

    UserPresenter addRoleToUser (UUID userId, UUID roleId);

    UserPresenter deleteRoleToUser (UUID userId, UUID roleId);

    UserPresenter userToUserPresenter(User user);

    List<UserPresenter> getUsers();

}
