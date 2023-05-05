package com.nhs.management.serviceImpl;

import com.nhs.management.entity.Skill;
import com.nhs.management.exception.ResourceNotFoundException;
import com.nhs.management.repository.EmployeeSkillsServiceRepository;
import com.nhs.management.service.EmployeeSkillsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class EmployeeSkillsServiceImpl implements EmployeeSkillsService {

    @Autowired
    EmployeeSkillsServiceRepository skillsServiceRepository;


    /**
     * This method used to fetch the skill Details based on ID.
     *
     * @param skillId This value represent Skill ID.
     * @return This Method return the Skill Details based on the Skill ID.
     */
    @Override
    public Optional<Skill> getSkillDetails(Long skillId) {
        log.debug("getSkillDetails() details to be request for the skill ID :: {} ", skillId);
        return skillsServiceRepository.findById(skillId);
    }

    /**
     * This method is used to update the Skill details based on skill ID.
     *
     * @param skillId This represent Skill ID.
     * @return This method return updated Skill Detail.
     */
    @Override
    public Optional<Skill> updateSkillDetails(Long skillId, Skill skill) {

        Skill skill_ = skillsServiceRepository.findById(skillId).get();

        if (Objects.isNull(skill_)) {
            throw new ResourceNotFoundException("Skill Details not found with ID " + skillId);
        }
        skill_.setLevel(skill.getLevel());
        skill_.setName(skill.getName());
        log.debug("updateSkillDetails() Skill details to be updated for the skill ID :: {}, value :: ", skillId, skill_);
        return Optional.of(skillsServiceRepository.save(skill_));

    }

    /**
     * This Method is used to delete skill using Skill ID.
     *
     * @param id This represent Skill ID.
     */
    @Override
    public void deleteSkillDetail(Long id) {
        if (!skillsServiceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill Details not found with ID " + id);
        }
        skillsServiceRepository.deleteById(id);
        log.info("deleteSkillDetail() Skill details successfully remove for the skill ID :: {}", id);
    }


}
