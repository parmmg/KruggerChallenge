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
import java.util.stream.Collectors;

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


    @Override
    public UserPresenter createUserByEmployee(Employee employee) {
        Optional<User> userOptional = userRepository.findById(employee.getId());
        if (userOptional.isPresent()) {
            throw new ValidationException("User already exist");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName("Employee"));
        User user = User.builder()
                .employee(employee)
                .userName(employee.getFirstName().replaceAll(" ", "").toLowerCase().trim() + "." + employee.getLastName().replaceAll(" ", "").toLowerCase().trim())
                .password(passwordEncoder.encode(employee.getDni()))
                .roles(roles)
                .build();
        user = userRepository.save(user);
        return userToUserPresenter(user);
    }

    @Override
    public UserPresenter addRoleToUser(UUID userId, UUID roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ValidationException("User Not Found"));
        Role role = roleService.findById(roleId);
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
        return userToUserPresenter(user);
    }

    @Override
    public UserPresenter deleteRoleToUser(UUID userId, UUID roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ValidationException("User Not Found"));
        Role role = roleService.findById(roleId);
        if (user.getRoles().contains(role)) {
            user.getRoles().remove(role);
            userRepository.save(user);
        }
        return userToUserPresenter(user);
    }

    @Override
    public UserPresenter userToUserPresenter(User user) {
        if (user==null)
            return null;
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

    @Override
    public List<UserPresenter> getUsers() {
        return userRepository.findAll().stream().filter(user -> user.getEmployee().getActive()).map(this::userToUserPresenter).collect(Collectors.toList());
    }

}