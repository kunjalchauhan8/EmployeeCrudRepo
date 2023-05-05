package com.nhs.management.service;

import com.nhs.management.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    /**
     * This method use to get all the Data for the Employee with Skills.
     *
     * @return Optional<List < Employee>> return list of all Employee.
     */
    public List<Employee> getAllEmployeeDetails();

    /**
     * This Method use to get Employee details based on the ID.
     *
     * @return Optional<Employee> return Employee details along with the skill.
     */
    public Optional<Employee> getEmployeeDetails(Long id);

    /**
     * This Method is used to create new Employee.
     *
     * @param employee This represents Employee Details
     * @return This Method return the created Employee with Employee ID.
     */
    public Optional<Employee> createNewEmployee(Employee employee);

    /**
     * This method use to remove the Employee Details.
     *
     * @param id This represents ID of the Employee.
     */
    public void removeEmployeeDetails(Long id);

    /**
     * This Method used to update employee Details.
     *
     * @param employee This represents the required update details.
     * @return This method return updated employee details.
     */
    public Optional<Employee> updateEmployeeDetails(Employee employee);
}
