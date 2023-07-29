package service;

import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap;

    public EmployeeServiceImpl(List<Employee> employeeMap) {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        return employeeMap.put(employee.getFullName(), employee);
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.remove(employee);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeMap.values();
    }
}
