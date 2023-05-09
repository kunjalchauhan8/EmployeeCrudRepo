package com.nhs.management.serviceImpl;

import com.nhs.management.entity.Employee;
import com.nhs.management.entity.Level;
import com.nhs.management.entity.Skill;
import com.nhs.management.repository.EmployeeServiceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceImplTest {

    @Autowired
    EmployeeServiceRepository employeeServiceRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void createNewEmployee() {
        Skill skill1 = Skill.builder().level(Level.WORKING).
                name("Communication").build();

        Skill skill2 = Skill.builder().level(Level.EXPERT).
                name("Computer").build();
        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        Employee employee = Employee.builder()
                .firstName("First")
                .lastName("Last")
                .skills(skills)
                .build();

        employeeServiceRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void getAllEmployeeDetails() {
        List<Employee> employeeList = employeeServiceRepository.findAll();
        Assertions.assertThat(employeeList.size()).isEqualTo(5);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    void getEmployeeDetails() {

        Employee employee = employeeServiceRepository.findById(1L).get();

        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void updateEmployeeDetails() {
        Employee employee = employeeServiceRepository.findById(1L).get();

        employee.setLastName("LastName");

        Employee employeeUpdated = employeeServiceRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getLastName()).isEqualTo("LastName");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    void updateEmployeeDetailsWithSkills() {
        Employee employee = employeeServiceRepository.findById(1L).get();

        employee.setLastName("LastName");
        Skill skill1 = Skill.builder().level(Level.PRACTITIONER).
                name("Communication").build();
        employee.getSkills().add(skill1);
        Employee employeeUpdated = employeeServiceRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getLastName()).isEqualTo("LastName");
        Assertions.assertThat(employeeUpdated.getSkills().size()).isEqualTo(3);
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    void removeEmployeeDetails() {
        employeeServiceRepository.deleteById(1L);
        List<Employee> employeeList = employeeServiceRepository.findAll();
        Assertions.assertThat(employeeList.size()).isEqualTo(4);
    }
}