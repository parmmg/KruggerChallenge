package com.krugger.challenge.service.impl;

import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.EmployeeVaccinePresenter;
import com.krugger.challenge.repository.EmployeeVaccineRepository;
import com.krugger.challenge.entity.EmployeeVaccine;
import com.krugger.challenge.service.EmployeeVaccineService;
import com.krugger.challenge.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmployeeVaccineServiceImpl implements EmployeeVaccineService {
    @Autowired
    private EmployeeVaccineRepository employeeVaccineRepository;

    @Autowired
    private VaccineService vaccineService;

    @Override
    public EmployeeVaccine saveEmployeeVaccine(EmployeeVaccinePresenter employeeVaccinePresenter) {
        EmployeeVaccine employeeVaccine = employeeVaccinePresenterToEmployeeVaccine(employeeVaccinePresenter);
        return employeeVaccineRepository.save(employeeVaccine);
    }

    @Override
    public String delete(UUID id) {
        Optional<EmployeeVaccine> employeeVaccine = employeeVaccineRepository.findById(id);
        if (employeeVaccine.isPresent()) {
            employeeVaccine.get().setActive(false);
            employeeVaccineRepository.save(employeeVaccine.get());
            return "deleted correctly";
        }
        return "vaccine not found";
    }

    @Override
    public EmployeeVaccine employeeVaccinePresenterToEmployeeVaccine(EmployeeVaccinePresenter employeeVaccinePresenter) {
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = formatter.parse(employeeVaccinePresenter.getDate());
        } catch (ParseException e) {
            throw new ValidationException("Date Format Not Valid");
        }


        return EmployeeVaccine.builder()
                .id(employeeVaccinePresenter.getId() != null
                        ? employeeVaccinePresenter.getId() : UUID.randomUUID())
                .date(date)
                .dose(employeeVaccinePresenter.getDose())
                .vaccine(vaccineService.findById(employeeVaccinePresenter.getVaccinePresenter().getId()))
                .active(employeeVaccinePresenter.getActive())
                .build();
    }

    @Override
    public EmployeeVaccinePresenter employeeVaccineToEmployeeVaccinePresenter(EmployeeVaccine employeeVaccine) {
        return EmployeeVaccinePresenter.builder()
                .id(employeeVaccine.getId())
                .date(employeeVaccine.getDate().toString())
                .dose(employeeVaccine.getDose())
                .vaccinePresenter(vaccineService.vaccineToVaccinePresenter(employeeVaccine.getVaccine()))
                .build();
    }

}
