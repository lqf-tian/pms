package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getManagers();

    Employee login(Employee employee);

    int saveInfo(Employee employee);
}
