package com.nhs.management.service;

import com.nhs.management.entity.Skill;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EmployeeSkillsService {

    /**
     * This method used to fetch the skill Details based on ID.
     *
     * @param skillId This value represent Skill ID.
     * @return This Method return the Skill Details based on the Skill ID.
     */
    Optional<Skill> getSkillDetails(Long skillId);

    /**
     * This method is used to update the Skill details based on skill ID.
     *
     * @param skillId This represent Skill ID.
     * @return This method return updated Skill Detail.
     */
    Optional<Skill> updateSkillDetails(Long skillId, Skill skill);

    /**
     * This Method is used to delete skill using Skill ID.
     *
     * @param id This represent Skill ID.
     */
    void deleteSkillDetail(Long id);
}
