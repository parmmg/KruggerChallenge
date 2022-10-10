package com.krugger.challenge.repository;

import com.krugger.challenge.entity.EmployeeVaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeVaccineRepository extends CrudRepository<EmployeeVaccine, UUID> {

    Optional<EmployeeVaccine> findByEmployeeId(UUID id);

}
