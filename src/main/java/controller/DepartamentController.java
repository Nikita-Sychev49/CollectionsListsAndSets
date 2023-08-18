package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DepartamentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    private final DepartamentService service;

    public DepartamentController(DepartamentService service) {
        this.service = service;
    }

    @GetMapping("/max-salary")
    public double max(@RequestParam int departamentId) {
        return service.maxSalary(departamentId);
    }

    @GetMapping("/min-salary")
    public double min(@RequestParam int departamentId) {
        return service.minSalary(departamentId);
    }

    @GetMapping("/all")
    public List<Employee> findAllByDept(@RequestParam int departamentId) {
        return service.findAllByDept(departamentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> groopByDept() {
        return service.groopByDept();
    }
}
