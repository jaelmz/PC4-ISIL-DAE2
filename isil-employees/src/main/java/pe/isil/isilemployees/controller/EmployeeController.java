package pe.isil.isilemployees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.isilemployees.model.Address;
import pe.isil.isilemployees.model.Employee;
import pe.isil.isilemployees.service.AddressService;
import pe.isil.isilemployees.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public String getEmployee(Model model){
        model.addAttribute("employee",employeeService.findAll());
        return "employee";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(Employee employee, Model model)
    {
        employeeService.create(employee);

        List<Employee> employeees = employeeService.findAll();
        model.addAttribute("employee",employeees);
        return "employee";
    }

    @GetMapping("employee/add")
    public String addEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return "employee-add";
    }

    @GetMapping("/employee/edit/{add_id}")
    public String getStudentForUpdate(@PathVariable Long add_id,
                                      Model model) {

        Employee currentEmployee = employeeService.findById(add_id);
        model.addAttribute("employee", currentEmployee);
        return "employee-edit";
    }

    @PostMapping("/employee/update/{add_id}")
    public String updateEmployee(@PathVariable Long add_id,
                                Employee employee,
                                Model model){
        employeeService.update(employee);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employee",employees);
        return "employee";
    }

    @GetMapping("employee/delete/{emp_id}")
    public String deleteEmployee(@PathVariable Long emp_id,
                                Model model){
        Employee currentEmployee = employeeService.findById(emp_id);

        employeeService.delete(emp_id);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employee",employees);
        return "employee";
    }
}

