package com.krugger.challenge.service;

import com.krugger.challenge.entity.Employee;
import com.krugger.challenge.entity.EmployeeVaccine;
import com.krugger.challenge.presentation.presenter.EmployeePresenter;
import com.krugger.challenge.presentation.presenter.EmployeeVaccinePresenter;

import java.util.UUID;

public interface EmployeeVaccineService {
    EmployeeVaccine saveEmployeeVaccine(EmployeeVaccinePresenter employeeVaccinePresenter);

    String delete(UUID id);

    public EmployeeVaccine employeeVaccinePresenterToEmployeeVaccine(EmployeeVaccinePresenter employeeVaccinePresenter);

    EmployeeVaccinePresenter employeeVaccineToEmployeeVaccinePresenter(EmployeeVaccine employeeVaccine);
}
