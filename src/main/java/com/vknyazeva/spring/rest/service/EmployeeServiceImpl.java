package com.vknyazeva.spring.rest.service;

import  com.vknyazeva.spring.rest.dao.EmployeeDAO;
import com.vknyazeva.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// данный класс связует controller и DAO, поэтому мы всю бизнесс-логику прописываем через него
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional     //spring берет на себя ответственность за открытие/зкрытие транзакций
    public List<Employee> getAllEmployees() {

        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {

        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {

        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {

        employeeDAO.deleteEmployee(id);
    }
}
