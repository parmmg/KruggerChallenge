package com.krugger.challenge.service.impl;

import com.krugger.challenge.exception.ValidationException;
import com.krugger.challenge.presentation.presenter.EmployeeVaccinePresenter;
import com.krugger.challenge.repository.EmployeeRepository;
import com.krugger.challenge.enumerator.Status;
import com.krugger.challenge.entity.Employee;
import com.krugger.challenge.entity.EmployeeVaccine;
import com.krugger.challenge.presentation.presenter.EmployeePresenter;
import com.krugger.challenge.service.EmployeeService;
import com.krugger.challenge.service.EmployeeVaccineService;
import com.krugger.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeVaccineService employeeVaccineService;

    @Autowired
    private UserService userService;

    @Override
    public Employee saveEmployee(EmployeePresenter employeePresenter) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = formatter.parse(employeePresenter.getBirthDate());
        } catch (ParseException e) {
            throw new ValidationException(e);
        }
        boolean createUser = false;
        Employee employee = new Employee();
        if (employeePresenter.getId() != null) {
            employee = employeeRepository.findById(employeePresenter.getId()).orElseThrow(() -> new ValidationException("Employee Not Exist"));
        } else {
            employee.setId(UUID.randomUUID());
            createUser = true;
        }
        employee.setDni(employeePresenter.getDni());
        employee.setFirstName(employeePresenter.getFirstName());
        employee.setLastName(employeePresenter.getLastName());
        employee.setMail(employeePresenter.getMail());
        employee.setBirthDate(date);
        employee.setAddress(employeePresenter.getAddress());
        employee.setPhone(employeePresenter.getPhone());
        employee.setStatus(Status.valueOf(employeePresenter.getStatus()));
        employee.setActive(employeePresenter.getActive());
        employee = employeeRepository.save(employee);
        if (!employeePresenter.getEmployeeVaccinePresenters().isEmpty()) {
            Set<EmployeeVaccine> employeeVaccines = new HashSet<>();
            Employee finalEmployee = employee;
            employeePresenter.getEmployeeVaccinePresenters().forEach(employeeVaccinePresenter -> {
//                EmployeeVaccine employeeVaccine = employeeVaccineService.
//
//                employeeVaccines.add();
//
            });
            employeeVaccines.forEach(employeeVaccine -> {
                employeeVaccine.setEmployee(finalEmployee);
//                employeeVaccineService.saveEmployeeVaccine(employeeVaccine);
            });
            employee.setEmployeeVaccines(employeeVaccines);
        }
        if (createUser) {
            userService.createUserByEmployee(employee);
        }
        return employee;
    }

    @Override
    public List<EmployeePresenter> findAll() {
        List<EmployeePresenter> employeePresenters = new ArrayList<>();
        employeeRepository.findByActiveTrue().forEach(employee -> employeePresenters.add(employeeToEmployeePresenter(employee)));
        return employeePresenters;
    }

    @Override
    public EmployeePresenter getEmployeeById(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return employeeToEmployeePresenter(employee);
    }

    @Override
    public EmployeePresenter getEmployeeByDni(String dni) {
        Employee employee = employeeRepository.findByDni(dni).orElse(null);
        return employeeToEmployeePresenter(employee);
    }


    @Override
    public EmployeePresenter employeeToEmployeePresenter(Employee employee) {
        if (employee==null)
            return null;
        List<EmployeeVaccinePresenter> employeeVaccinePresenter = new ArrayList<>();
        employee.getEmployeeVaccines().stream().filter(EmployeeVaccine::getActive).forEach(vaccine -> employeeVaccinePresenter.add(employeeVaccineService.employeeVaccineToEmployeeVaccinePresenter(vaccine)));
        return EmployeePresenter.builder()
                .id(employee.getId())
                .dni(employee.getDni())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .mail(employee.getMail())
                .birthDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(employee.getBirthDate()))
                .address(employee.getAddress())
                .phone(employee.getPhone())
                .status(employee.getStatus().name())
                .active(employee.getActive())
                .employeeVaccinePresenters(employeeVaccinePresenter)
                .build();
    }

    @Override
    public String delete(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employee.get().setActive(false);
            employeeRepository.save(employee.get());
            return "deleted correctly";
        }
        return "employee not found";
    }

    @Override
    public List<EmployeePresenter> searchEmployees(String value, Date initDate, Date endDate, String status) {
        Status[] vaccineStatus;
        try {
            vaccineStatus = status==null || status.isEmpty() ? Status.values() : new Status[]{Status.valueOf(status)};
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Status Not Exist");
        }
        List<EmployeePresenter> employeePresenters = new ArrayList<>();
        List<Employee> employees = employeeRepository.findByFilters(value, initDate, endDate, vaccineStatus);
        employees.stream().filter(Employee::getActive).forEach(employee -> employeePresenters.add(employeeToEmployeePresenter(employee)));
        return employeePresenters;
    }

    @Override
    public List<EmployeePresenter> getEmployeesByStatus(String status) {
        List<EmployeePresenter> employeesPresenter = new ArrayList<>();
        Status[] vaccineStatus;
        try {
            vaccineStatus = status==null || status.isEmpty() ? Status.values() : new Status[]{Status.valueOf(status)};
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Status Not Exist");
        }
        List<Employee> employees = employeeRepository.findByStatus(vaccineStatus);
        List<EmployeePresenter> employeePresenters = new ArrayList<>();
        employees.stream().filter(Employee::getActive).forEach(employee -> employeePresenters.add(employeeToEmployeePresenter(employee)));
        return employeesPresenter;
    }

    @Override
    public List<EmployeePresenter> getEmployeesByVaccineDate(Date initDate, Date endDate) {
        List<Employee> employees = employeeRepository.findByFilters("", initDate, endDate, null);
        List<EmployeePresenter> employeePresenters = new ArrayList<>();
        employees.stream().filter(Employee::getActive).forEach(employee -> employeePresenters.add(employeeToEmployeePresenter(employee)));
        return employeePresenters;
    }
}
