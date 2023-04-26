package com.saksoft.services;

import com.saksoft.payloads.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer employeeId);
    EmployeeDto getEmployeeById(Integer employeeId);
    List<EmployeeDto> getAllEmployee();
    void deleteEmployee(Integer employeeId);
    EmployeeDto getUserByEmail(String email);


}
