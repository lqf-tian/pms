package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeControllerImp implements EmployeeService{

    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getManagers() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> employees= employeeMapper.selectByExample(example);
        return employees;
    }

    @Override
    public Employee login(Employee employee) {
        EmployeeExample example= new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (employees != null && employees.size()>0){
            employee = employees.get(0);
            return employee;
        }
        return null;
    }

    @Override
    public int saveInfo(Employee employee) {
        employeeMapper.saveInfoEmp(employee);
        return  employee.getEid();
    }
}
