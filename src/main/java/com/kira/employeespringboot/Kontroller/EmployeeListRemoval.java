package com.kira.employeespringboot.Kontroller;

import com.kira.employeespringboot.model.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeListRemoval {
    public static void main(String[] args) {
        List<Employee> empList1 = new ArrayList();
        empList1.add(new Employee(1L,"ram","kumar","a@gmail.com"));
        empList1.add(new Employee(2L,"ram","kumar","a@gmail.com"));
        empList1.add(new Employee(3L,"ram","kumar","a@gmail.com"));
        empList1.add(new Employee(4L,"ram","kumar","a@gmail.com"));

        List<Employee> empList2 = new ArrayList();
//        empList2.add(new Employee(5L,"ram","kumar","a@gmail.com"));
//        empList2.add(new Employee(2L,"ram","kumar","a@gmail.com"));
//        empList2.add(new Employee(3L,"ram","kumar","a@gmail.com"));
//        empList2.add(new Employee(1L,"ram","kumar","a@gmail.com"));



        empList1.removeIf(employee ->
                empList2.stream()
                        .map(emp->emp.getEmpId())
                        .collect(Collectors.toList())
                        .contains(employee.getEmpId()));
        System.out.println(empList1);



    }
}
