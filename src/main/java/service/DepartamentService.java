package service;

import exeption.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartamentService {
    private final EmployeeServiceImpl employeeService;

    public DepartamentService(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public double maxSalary(int deptId) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartament() == deptId)
                .map(Employee::getSalary)
                .max(Comparator.comparingDouble(o -> o))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public double minSalary(int deptId) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartament() == deptId)
                .map(Employee::getSalary)
                .min(Comparator.comparingDouble(o -> o))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public List<Employee> findAllByDept(int deptId) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartament() == deptId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groopByDept() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));
    }

}
