package com.krugger.challenge.repository;

import com.krugger.challenge.entity.Employee;
import com.krugger.challenge.enumerator.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    @Query("SELECT e " +
            "FROM Employee e " +
            "left JOIN e.employeeVaccines ev " +
            "WHERE (LOWER(e.dni) like LOWER(CONCAT('%',:value,'%')) " +
            "OR LOWER(e.firstName) like LOWER(CONCAT('%',:value,'%')) " +
            "OR LOWER(e.lastName) like LOWER(CONCAT('%',:value,'%'))) " +
            "AND (cast(ev.date as string) between coalesce(cast(:initDate as string), cast(ev.date as string)) " +
            "and coalesce(cast(:endDate as string), cast(ev.date as string))) " +
            "AND e.status in :status ")
    List<Employee> findByFilters(@Param("value") String value,
                                 @Param("initDate") Date initDate,
                                 @Param("endDate") Date endDate,
                                 @Param("status") Status[] status);

    List<Employee> findByActiveTrue();

    List<Employee> findByStatus(Status[] status);

    Optional<Employee> findByDni(String dni);

    @Query("SELECT e " +
            "FROM Employee e " +
            "JOIN e.employeeVaccines ev " +
            "JOIN ev.vaccine v " +
            "WHERE v.id = :id")
    List<Employee> findEmployeesByVaccineId(UUID id);

}
