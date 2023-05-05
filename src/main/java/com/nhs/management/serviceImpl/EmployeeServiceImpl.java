package com.nhs.management.serviceImpl;

import com.nhs.management.entity.Employee;
import com.nhs.management.exception.ResourceNotFoundException;
import com.nhs.management.repository.EmployeeServiceRepository;
import com.nhs.management.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class represents the implementation of the Employee service Methods.
 */
@Component
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeServiceRepository employeeRepository;

    /**
     * This method use to get all the Data for the Employee with Skills.
     *
     * @return Optional<List < Employee>> return list of all Employee.
     */
    @Override
    public List<Employee> getAllEmployeeDetails() {
        log.debug("getAllEmployeeDetails() Data reqeusted. ");
        return employeeRepository.findAll();
    }


    /**
     * This Method use to get Employee details based on the ID.
     *
     * @return Optional<Employee> return Employee details along with the skill.
     */
    @Override
    public Optional<Employee> getEmployeeDetails(Long id) {
        log.debug("getEmployeeDetails() Employee data requested for Employee Id :: {} ", id);
        return employeeRepository.findById(id);
    }

    /**
     * This Method is used to create new Employee.
     *
     * @param employee This represents Employee Details
     * @return This Method return the created Employee with Employee ID.
     */
    @Override
    public Optional<Employee> createNewEmployee(Employee employee) {
        if (Objects.isNull(employee)) {
            log.error("createNewEmployee() Employee request is not Valid!  ");
            throw new IllegalArgumentException("Employee request is not Valid!");

        }
        return Optional.of(employeeRepository.save(employee));
    }

    /**
     * This method use to remove the Employee Details.
     *
     * @param id This represents ID of the Employee.
     */
    @Override
    public void removeEmployeeDetails(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee Details not found with ID " + id);
        }
        employeeRepository.deleteById(id);
        log.info("removeEmployeeDetails() Employee Details successfully removed with ID " + id);

    }

    /**
     * This Method used to update employee Details.
     *
     * @param employee This represents the required update details.
     * @return This method return updated employee details.
     */
    @Override
    public Optional<Employee> updateEmployeeDetails(Employee employee) {
        if (Objects.isNull(employee)) {
            throw new IllegalArgumentException("Employee request is not Valid!");
        }
        Optional<Employee> emp = employeeRepository.findById(employee.getId());
        if (!emp.isPresent()) {

            throw new ResourceNotFoundException("Employee Details not found with ID " + employee.getId());
        }
        Employee employee_ = emp.get();
        employee_.setFirstName(employee.getFirstName());
        employee_.setLastName(employee.getFirstName());
        employee_.setSkills(employee.getSkills());
        log.info("updateEmployeeDetails() Employee Details to be updated for the Employee ID  " + employee.getId());
        return Optional.of(employeeRepository.save(employee_));
    }


}
