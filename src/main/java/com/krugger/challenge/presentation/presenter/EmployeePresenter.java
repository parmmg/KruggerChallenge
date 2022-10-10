package com.krugger.challenge.presentation.presenter;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePresenter implements Comparable<EmployeePresenter> {
    private UUID id;
    private String lastName;
    private String firstName;
    private String birthDate;
    private String dni;
    private String address;
    private String mail;
    private String status;
    private String phone;
    private Boolean active;
    @Builder.Default
    private List<EmployeeVaccinePresenter> employeeVaccinePresenters = new ArrayList<>();

    @Override
    public int compareTo(EmployeePresenter other) {
        return this.getDni().compareTo(other.getDni());
    }

}
