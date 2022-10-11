package com.krugger.challenge.util;

import com.krugger.challenge.entity.*;
import com.krugger.challenge.presentation.presenter.*;

import java.util.*;

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

    public EmployeeVaccine employeeVaccineFake() {
        return EmployeeVaccine.builder()
                .employee(employeeFake())
                .vaccine(vaccineFake())
                .id(UUID.randomUUID())
                .active(true)
                .dose(1)
                .date(new Date())
                .build();
    }
    public EmployeeVaccinePresenter employeeVaccinePresenterFake() {
        return EmployeeVaccinePresenter.builder()
                .vaccinePresenter(vaccinePresenterFake())
                .id(UUID.randomUUID())
                .active(true)
                .dose(1)
                .date("2022-10-10 00:00:00")
                .build();
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


    public Iterable<Employee> employeesFake () {
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeFake());
        return employees;
    }

    public EmployeePresenter employeePresenterFake () {
        return EmployeePresenter.builder()
                .id(UUID.randomUUID())
                .status("VACUNADO")
                .firstName("First Name")
                .lastName("Last Name")
                .dni("1234567890")
                .birthDate("2000-01-01 00:00:00")
                .build();
    }

    public User userFake() {
        Employee employeeFake = employeeFake();
        Set<Role> roles = new HashSet<>();
        roles.add(roleFake());
        return User.builder()
                .employee(employeeFake)
                .employeeId(employeeFake.getId())
                .userName(employeeFake.getFirstName().replaceAll(" ","").toLowerCase() + "." + employeeFake.getLastName().replaceAll(" ","").toLowerCase())
                .password(employeeFake.getDni())
                .roles(roles)
                .build();
    }

    public UserPresenter userPresenterFake() {
        Employee employeeFake = employeeFake();
        return UserPresenter.builder()
                .dni(employeeFake.getDni())
                .fullName(employeeFake.getFirstName()+ " " + employeeFake.getLastName())
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

    public RolePresenter rolePresenterFake() {
        return RolePresenter.builder()
                .id(UUID.randomUUID())
                .name("name")
                .build();
    }

    public Permission permissionFake() {
        return Permission.builder()
                .domainAction("name")
                .id(UUID.randomUUID())
                .name("name")
                .build();
    }

}
