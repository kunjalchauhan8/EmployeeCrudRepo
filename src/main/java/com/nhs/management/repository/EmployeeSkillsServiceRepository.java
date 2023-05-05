package com.nhs.management.repository;

import com.nhs.management.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This Employee Skills Repository Created for the purpose in future for complex implementation
 * we can implement more methods utilising the inbuilt repository methods.
 */
public interface EmployeeSkillsServiceRepository extends JpaRepository<Skill, Long> {

}
