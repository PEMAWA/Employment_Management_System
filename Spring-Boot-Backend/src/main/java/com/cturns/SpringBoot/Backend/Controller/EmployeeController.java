package com.cturns.SpringBoot.Backend.Controller;

import com.cturns.SpringBoot.Backend.Exception.ResourceNotFoundException;
import com.cturns.SpringBoot.Backend.Model.Employee;
import com.cturns.SpringBoot.Backend.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
private EmployeeRepository employeeRepository;

    //Get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){

        return  employeeRepository.findAll();
    }

    //create add employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);

    }
    //get Employee by id rest API
    @GetMapping("/employees/{id}")
    public ResponseEntity <Employee> getEmployeeById(@PathVariable Long id){
        Employee employee =employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " +id+ " does not exist"));
return ResponseEntity.ok(employee);
    }

    //update employee rest api
    @PutMapping("/employees/{id}")
    public  ResponseEntity<Employee> updateEmployee( @PathVariable Long id ,@RequestBody Employee employeeDetails){
        Employee employee =employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " +id+ " does not exist"));
employee.setFirstName(employeeDetails.getFirstName());
employee.setLastName(employeeDetails.getLastName());
employee.setEmailId(employeeDetails.getEmailId());
Employee updatedEmployee = employeeRepository.save(employee);
return  ResponseEntity.ok(updatedEmployee);
    }
    //delete employee Rest API
    @DeleteMapping("/employees/{id}")
    public ResponseEntity <Map<String,Boolean>>deleteEmployee( @PathVariable Long id){
        Employee employee =employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " +id+ " does not exist"));
        employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted" , Boolean.TRUE);
        return  ResponseEntity.ok(response);

    }

}
