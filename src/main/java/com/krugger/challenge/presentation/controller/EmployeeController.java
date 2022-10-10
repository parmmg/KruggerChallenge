package com.krugger.challenge.presentation.controller;

import com.krugger.challenge.presentation.presenter.EmployeePresenter;
import com.krugger.challenge.service.EmployeeService;
import com.sun.istack.NotNull;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Generated
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public EmployeePresenter saveEmployee(@RequestBody @NotNull EmployeePresenter employeePresenter) {
        return employeeService.employeeToEmployeePresenter(employeeService.saveEmployee(employeePresenter));
    }

    @GetMapping("/findAllEmployes")
    public List<EmployeePresenter> findAll() {
        return employeeService.findAll();
    }


    @GetMapping("/getEmployeeByDni")
    public EmployeePresenter getEmployeeByDni(@RequestParam @NotNull String dni)  {
        return employeeService.getEmployeeByDni(dni);
    }

    @GetMapping("/getEmployeeById")
    public EmployeePresenter getEmployeeById(@RequestParam @NotNull UUID employeeId)  {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam @NotNull UUID employeeId) {
        return employeeService.delete(employeeId);
    }

    @GetMapping("/findEmployessByStatus")
    public List<EmployeePresenter> getEmployeesByStatus(@RequestParam(value = "status") String status) {
        return employeeService.getEmployeesByStatus(status);
    }

    @GetMapping("/findEmployessByVaccineDate")
    public List<EmployeePresenter> getEmployeesByVaccineDate(
            @RequestParam(value = "initDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date initDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)  {
        return employeeService.getEmployeesByVaccineDate(initDate, endDate);
    }

    @GetMapping("/searchEmployees")
    public List<EmployeePresenter> searchEmployees(@RequestParam(value = "value", required = false) String value,
                                                   @RequestParam(value = "initDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date initDate,
                                                   @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                   @RequestParam(value = "status", required = false) String status) {
        return employeeService.searchEmployees(value, initDate, endDate, status);
    }

}
