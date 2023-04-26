package com.saksoft.services;

import com.saksoft.entities.Employee;
import com.saksoft.exceptions.ResourceNotFoundException;
import com.saksoft.payloads.EmployeeDto;
import com.saksoft.repositories.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = dtoToEntity(employeeDto);
        Employee savedEmployee = this.employeeRepo.save(employee);
        //entity to dto
        EmployeeDto newDto = entityToDto(savedEmployee);
        return newDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAge(employeeDto.getAge());
        Employee updatEmployee = this.employeeRepo.save(employee);
        EmployeeDto employeeDto1 = this.entityToDto(updatEmployee);
        return employeeDto1;
    }

    @Override
    public EmployeeDto getEmployeeById(Integer employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return this.entityToDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = this.employeeRepo.findAll();
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> this.entityToDto(employee)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
        this.employeeRepo.delete(employee);

    }
    @Override
    public EmployeeDto getUserByEmail(String email) {
        Employee employeeEmail = this.employeeRepo.findByEmail(email).
                orElseThrow(() -> new ResourceNotFoundException("user not found with given Email"));
        return this.entityToDto(employeeEmail);
    }
    private EmployeeDto entityToDto(Employee saveEmployee) {
        return modelMapper.map(saveEmployee, EmployeeDto.class);
    }
    private Employee dtoToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }


}
