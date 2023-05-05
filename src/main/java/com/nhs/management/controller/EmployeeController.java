package com.nhs.management.controller;

import com.nhs.management.entity.Employee;
import com.nhs.management.exception.Error;
import com.nhs.management.service.EmployeeService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nhs/")
@Slf4j
@Tag(description = "API is useful to manage resources and Skill", name = "Employee Service API")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Fetch All Employees data.")
    @GetMapping(path = "/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesDetails() {
        Optional<List<Employee>> employeeList = Optional.ofNullable(
                employeeService.getAllEmployeeDetails());

        return employeeList.map(employees -> new ResponseEntity<>(employees, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Fetch Employee data with input of Employee ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Execution!"),
            @ApiResponse(responseCode = "500", description = "Server Error!",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping(path = "/employee/{id}")
    public ResponseEntity<Employee> getEmployeeDetail(
            @Parameter(description = "Employee ID")
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Employee> employee = employeeService.getEmployeeDetails(id);
        return employee.map(employee_ -> new ResponseEntity<>(employee.get(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Remove Employee data with input of Employee ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Execution!"),
            @ApiResponse(responseCode = "500", description = "Server Error!",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @DeleteMapping(path = "/employee/{id}")
    public ResponseEntity<?> removeEmployee(
            @Parameter(description = "Employee ID")
            @PathVariable(name = "id", required = true) Long id) {
        employeeService.removeEmployeeDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create new Employee data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Execution!"),
            @ApiResponse(responseCode = "500", description = "Server Error!",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PostMapping(path = "/employee/")
    public ResponseEntity<Employee> addNewEmployee(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of Employee.")
            @RequestBody Employee employee) {
        Optional<Employee> employeeResponse = employeeService.createNewEmployee(employee);
        return employeeResponse.map(employee_ -> new ResponseEntity<>(employeeResponse.get(), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Update Existing Employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Execution!"),
            @ApiResponse(responseCode = "500", description = "Server Error!",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PutMapping(path = "/employee/")
    public ResponseEntity<Employee> updateEmployee(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of Employee.")
            @RequestBody Employee employee) {
        Optional<Employee> employeeResponse = employeeService.updateEmployeeDetails(employee);

        return employeeResponse.map(employee_ -> new ResponseEntity<>(employeeResponse.get(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }


}
