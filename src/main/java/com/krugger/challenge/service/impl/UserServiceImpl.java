package com.krugger.challenge.service.impl;

import com.krugger.challenge.entity.Role;
import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.repository.UserRepository;
import com.krugger.challenge.entity.Employee;
import com.krugger.challenge.entity.User;
import com.krugger.challenge.presentation.presenter.RolePresenter;
import com.krugger.challenge.presentation.presenter.UserPresenter;
import com.krugger.challenge.service.RoleService;
import com.krugger.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(RoleService roleService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public UserPresenter loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUserNameAndPassword(username, passwordEncoder.encode(password));
        return user.map(this::userToUserPresenter).orElse(null);
    }

    @Override
    public UserPresenter createUserByEmployee(Employee employee) {
        Optional<User> user = userRepository.findById(employee.getId());
        if (user.isPresent()) {
            throw new ValidationException("User already exist");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName("Empleado"));
        return userToUserPresenter(userRepository.save(User.builder()
                .employee(employee)
                .userName(employee.getFirstName().replaceAll(" ", "").toLowerCase().trim() + "." + employee.getLastName().replaceAll(" ", "").toLowerCase().trim())
                .password(passwordEncoder.encode(employee.toString()))
                .roles(roles)
                .build()));
    }

    @Override
    public UserPresenter userToUserPresenter(User user) {
        List<RolePresenter> rolePresenters = new ArrayList<>();
        user.getRoles().forEach(role -> rolePresenters.add(roleService.roleToRolePresenter(role)));
        return UserPresenter.builder()
                .id(user.getEmployee().getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .fullName(user.getEmployee().getFirstName() + " " + user.getEmployee().getLastName())
                .dni(user.getEmployee().getDni())
                .rolePresenters(rolePresenters)
                .build();
    }

}