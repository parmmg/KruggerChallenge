package com.krugger.challenge.service;

import com.krugger.challenge.entity.Vaccine;
import com.krugger.challenge.presentation.presenter.VaccinePresenter;

import java.util.List;
import java.util.UUID;

public interface VaccineService {

    VaccinePresenter vaccineToVaccinePresenter(Vaccine vaccine);

    Vaccine vaccinePresenterToVaccine(VaccinePresenter vaccinePresenter);

    List<VaccinePresenter> getVaccines();

    Vaccine findById(UUID id);
}
