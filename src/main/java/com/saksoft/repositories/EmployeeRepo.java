package com.saksoft.repositories;

import com.saksoft.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EmployeeRepo extends JpaRepository<Employee,Integer>
{
    Optional<Employee> findByEmail(String email);
}
