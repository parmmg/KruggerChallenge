package com.krugger.challenge.service;

import com.krugger.challenge.presentation.presenter.EmployeePresenter;
import com.krugger.challenge.entity.Employee;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee saveEmployee(EmployeePresenter employeePresenter);

    List<EmployeePresenter> findAll();

    EmployeePresenter getEmployeeById(UUID employeeId) ;

    EmployeePresenter getEmployeeByDni(String dni) ;

    EmployeePresenter employeeToEmployeePresenter(Employee workOrder) ;

    String delete(UUID id);

    List<EmployeePresenter> searchEmployees(String value, Date initDate, Date endDate, String status);

    List<EmployeePresenter> getEmployeesByStatus(String status);

    List<EmployeePresenter> getEmployeesByVaccineDate(Date initDate, Date endDate);

}
