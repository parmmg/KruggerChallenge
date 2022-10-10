package com.krugger.challenge.service;


import com.krugger.challenge.entity.User;
import com.krugger.challenge.presentation.presenter.UserPresenter;
import com.krugger.challenge.entity.Employee;

public interface UserService {

    UserPresenter loginUser(String userName, String password);
    UserPresenter createUserByEmployee(Employee employee);

    UserPresenter userToUserPresenter(User user);

}
