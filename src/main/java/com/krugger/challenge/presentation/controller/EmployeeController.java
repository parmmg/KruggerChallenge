package com.krugger.challenge.presentation.controller;

import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.EmployeePresenter;
import com.krugger.challenge.service.EmployeeService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Generated
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/saveEmployee")
    public EmployeePresenter saveEmployee(@RequestBody @NotNull EmployeePresenter employeePresenter) {
        try {
            return employeeService.employeeToEmployeePresenter(employeeService.saveEmployee(employeePresenter));
        } catch (Exception e) {
            Throwable ex = e;
            while (ex.getCause()!=null && !ex.getCause().equals(ex))
                ex = ex.getCause();
            if (ex instanceof ConstraintViolationException) {
                throw new ValidationException("", ((ConstraintViolationException) ex).getConstraintViolations());
            } else {
                throw new ValidationException(ex.getMessage());
            }
        }
    }

    @GetMapping("/app/findAllEmployees")
    public List<EmployeePresenter> findAll() {
        return employeeService.findAll();
    }


    @GetMapping("/app/getEmployeeByDni")
    public EmployeePresenter getEmployeeByDni(@RequestParam @NotNull String dni)  {
        return employeeService.getEmployeeByDni(dni);
    }

    @GetMapping("/app/getEmployeeById")
    public EmployeePresenter getEmployeeById(@RequestParam @NotNull UUID employeeId)  {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/app/deleteEmployee")
    public String deleteEmployee(@RequestParam @NotNull UUID employeeId) {
        return employeeService.delete(employeeId);
    }

    @GetMapping("/app/findEmployeesByStatus")
    public List<EmployeePresenter> getEmployeesByStatus(@RequestParam(value = "status") String status) {
        return employeeService.getEmployeesByStatus(status);
    }

    @GetMapping("/app/findEmployeesByVaccineId")
    public Set<EmployeePresenter> getEmployeesByVaccineId(
            @RequestParam @NotNull UUID vaccineId)  {
        return employeeService.getEmployeesByVaccineId(vaccineId);
    }

    @GetMapping("/app/findEmployeesByVaccineDate")
    public List<EmployeePresenter> getEmployeesByVaccineDate(
            @RequestParam(value = "initDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date initDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)  {
        return employeeService.getEmployeesByVaccineDate(initDate, endDate);
    }

    @GetMapping("/app/searchEmployees")
    public List<EmployeePresenter> searchEmployees(@RequestParam(value = "value", required = false) String value,
                                                   @RequestParam(value = "initDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date initDate,
                                                   @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                   @RequestParam(value = "status", required = false) String status) {
        return employeeService.searchEmployees(value, initDate, endDate, status);
    }

}
