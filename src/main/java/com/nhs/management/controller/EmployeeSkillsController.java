package com.nhs.management.controller;

import com.nhs.management.entity.Skill;
import com.nhs.management.exception.Error;
import com.nhs.management.service.EmployeeSkillsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/nhs/")
@Slf4j
@Tag(description = "API is useful to manage Skills", name = "Skills Service API")
public class EmployeeSkillsController {

    @Autowired
    private EmployeeSkillsService employeeSkillsService;

    @Operation(summary = "Fetch skill data with input of skill ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Execution!"),
            @ApiResponse(responseCode = "500", description = "Server Error!",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping(path = "/skill/{id}/")
    public ResponseEntity<Skill> getSkillDetails(
            @Parameter(description = "Skill ID")
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Skill> skillDetail = employeeSkillsService.getSkillDetails(id);
        return skillDetail.map(skill -> new ResponseEntity<>(skill, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Update skill data with input of skill ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Execution!"),
            @ApiResponse(responseCode = "500", description = "Server Error!",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PutMapping(path = "/skill/{id}/")
    public ResponseEntity<Skill> updateSkillDetails(
            @Parameter(description = "Skill ID") @PathVariable(name = "id", required = true) Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Skill Request Object")
            @RequestBody Skill skill) {
        Optional<Skill> skillDetail = employeeSkillsService.updateSkillDetails(id, skill);
        return skillDetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @Operation(summary = "Delete skill data with input of skill ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Execution!"),
            @ApiResponse(responseCode = "500", description = "Server Error!",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @DeleteMapping(path = "/skill/{id}/")
    public ResponseEntity<?> deleteAllSkillsByEmployeeId(
            @Parameter(description = "Skill ID") @PathVariable(name = "id", required = true) Long id) {
        employeeSkillsService.deleteSkillDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
