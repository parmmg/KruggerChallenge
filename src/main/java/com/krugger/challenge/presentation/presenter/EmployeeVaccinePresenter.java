package com.krugger.challenge.presentation.presenter;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVaccinePresenter {

    private UUID id;
    private VaccinePresenter vaccinePresenter;
    private int dose;
    private String date;
    private Boolean active;
}
