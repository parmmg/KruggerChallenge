package com.krugger.challenge.presentation.controller;

import com.krugger.challenge.presentation.presenter.EmployeeVaccinePresenter;
import com.krugger.challenge.service.EmployeeVaccineService;
import com.sun.istack.NotNull;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Generated
@RestController
public class EmployeeVaccineController {

    @Autowired
    private EmployeeVaccineService employeeVaccineService;

    @PostMapping("/saveEmployeeVaccine")
    public EmployeeVaccinePresenter saveEmployeeVaccine(@RequestBody @NotNull EmployeeVaccinePresenter employeeVaccinePresenter) {
        return employeeVaccineService.employeeVaccineToEmployeeVaccinePresenter(employeeVaccineService.saveEmployeeVaccine(employeeVaccinePresenter));
    }

    @GetMapping("/deleteEmployeeVaccine")
    public String deleteEmployeeVaccine(@RequestParam @NotNull String id) {
        return employeeVaccineService.delete(UUID.fromString(id));
    }

}
