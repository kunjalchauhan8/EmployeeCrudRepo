package com.nhs.management.repository;

import com.nhs.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This Employee Repository Created for the purpose in future for complex implementation
 * we can implement more methods utilising the inbuilt repository methods.
 */
public interface EmployeeServiceRepository extends JpaRepository<Employee, Long> {

}
