package com.krugger.challenge.presentation.controller;

import com.krugger.challenge.presentation.presenter.VaccinePresenter;
import com.krugger.challenge.service.VaccineService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Generated
@RestController
public class VaccineController {
    @Autowired
    VaccineService vaccineService;

    @GetMapping("/employee/getVaccines")
    public List<VaccinePresenter> getVaccines() {
        return vaccineService.getVaccines();
    }

}
