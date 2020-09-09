package com.kira.employeespringboot.Kontroller;

import com.kira.employeespringboot.exception.ResourceNotFoundException;
import com.kira.employeespringboot.model.Employee;
import com.kira.employeespringboot.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
//class level
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // global for all controllers
    @Bean
    public WebMvcConfigurer configure(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/").allowedOrigins("http://localhost:3000");
            }
        };
    }
    //Method  level
//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
       return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody  Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId){
        return ResponseEntity.ok(employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not exist with ID:"+empId)));
    }

    @PutMapping("/employees/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId, @RequestBody Employee employee){

       Employee emp = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not exist with ID:"+empId));

       emp.setFirstName(employee.getFirstName());
       emp.setLastName(employee.getLastName());
       emp.setEmailId(employee.getEmailId());
       return ResponseEntity.ok(employeeRepository.save(emp));
    }

    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable  Long empId){
        Employee emp = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not exist with ID:"+empId));
        employeeRepository.deleteById(empId);
        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
