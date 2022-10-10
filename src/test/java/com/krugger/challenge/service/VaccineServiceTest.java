package com.krugger.challenge.service;

import com.krugger.challenge.entity.Vaccine;
import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.VaccinePresenter;
import com.krugger.challenge.repository.VaccineRepository;
import com.krugger.challenge.service.impl.VaccineServiceImpl;
import com.krugger.challenge.util.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VaccineServiceTest {

    @InjectMocks
    @Spy
    private VaccineService vaccineService = new VaccineServiceImpl();

    @Mock
    private VaccineRepository vaccineRepository;

    private final TestData testData = new TestData();

    @Test
    public void shouldGetVaccines() {
        when(vaccineRepository.findAll()).thenReturn(testData.vaccinesFakes());
        List<VaccinePresenter> vaccines = vaccineService.getVaccines();
        Assertions.assertThat(vaccines).isNotEmpty();
    }

    @Test
    public void shouldFindVaccineById() {
        when(vaccineRepository.findById(UUID.randomUUID())).thenReturn(Optional.of(testData.vaccineFake()));
        Vaccine vaccine = vaccineService.findById(UUID.randomUUID());
        Assertions.assertThat(vaccine).isNotNull();
    }

    @Test
    public void shouldGetValidationExceptionWhenVaccineNotExist() {
        Assertions.assertThatThrownBy(() -> vaccineService.findById(UUID.randomUUID())).isInstanceOf(ValidationException.class)
                .hasMessageContaining("Vaccine not found");
    }

    @Test
    public void shouldGetVaccineToVaccinePresenter() {
        Vaccine vaccine = testData.vaccineFake();
        VaccinePresenter vaccinePresenter = vaccineService.vaccineToVaccinePresenter(vaccine);
        Assertions.assertThat(vaccinePresenter.getName().equals(vaccine.getName()));
    }

    @Test
    public void shouldGetVaccinePresenterToVaccine() {
        VaccinePresenter vaccinePresenter = testData.vaccinePresenterFake();
        Vaccine vaccine = vaccineService.vaccinePresenterToVaccine(vaccinePresenter);
        Assertions.assertThat(vaccinePresenter.getName().equals(vaccine.getName()));
    }

}
