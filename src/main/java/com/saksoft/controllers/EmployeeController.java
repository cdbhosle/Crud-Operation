package com.saksoft.controllers;

import com.saksoft.payloads.ApiResponse;
import com.saksoft.payloads.EmployeeDto;
import com.saksoft.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createUserDto = this.employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer employeeId) {
        EmployeeDto updatedEmployee = this.employeeService.updateEmployee(employeeDto, employeeId);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
        this.deleteEmployee(employeeId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Employee deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        return ResponseEntity.ok(this.employeeService.getAllEmployee());
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getSingleEmployee(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDto> getUserByEmail(@PathVariable String email) {
        this.employeeService.getUserByEmail(email);
        return new ResponseEntity<>(this.employeeService.getUserByEmail(email), HttpStatus.OK);

    }



}
