package com.krugger.challenge.util;

import com.krugger.challenge.entity.*;
import com.krugger.challenge.presentation.presenter.VaccinePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestData {

    public Iterable<Vaccine> vaccinesFakes() {
        List<Vaccine> vaccines = new ArrayList<>();
        vaccines.add(vaccineFake());
        return vaccines;
    }

    public Vaccine vaccineFake() {
        return Vaccine.builder().id(UUID.randomUUID()).name("name").build();
    }

    public VaccinePresenter vaccinePresenterFake() {
        return VaccinePresenter.builder().id(UUID.randomUUID()).name("name").build();
    }

    public Employee employeeFake () {
        return Employee.builder()
                .id(UUID.randomUUID())
                .firstName("First Name")
                .lastName("Last Name")
                .dni("1234567890")
                .birthDate(new Date())
                .build();
    }

    public User userFake() {
        Employee employeeFake = employeeFake();
        return User.builder()
                .employee(employeeFake)
                .employeeId(employeeFake.getId())
                .userName(employeeFake.getFirstName().replaceAll(" ","").toLowerCase() + "." + employeeFake.getLastName().replaceAll(" ","").toLowerCase())
                .password(employeeFake.getDni())
                .build();
    }

    public Role roleFake() {
        return Role.builder()
                .id(UUID.randomUUID())
                .name("name")
                .build();
    }

    public Permission permissionFake() {
        return Permission.builder()
                .id(UUID.randomUUID())
                .name("name")
                .build();
    }

}
