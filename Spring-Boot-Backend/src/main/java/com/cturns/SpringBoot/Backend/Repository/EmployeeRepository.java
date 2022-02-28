package com.cturns.SpringBoot.Backend.Repository;

import com.cturns.SpringBoot.Backend.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee ,Long> {
}
