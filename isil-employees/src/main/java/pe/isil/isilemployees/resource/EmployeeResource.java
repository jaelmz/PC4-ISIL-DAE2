package pe.isil.isilemployees.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.isilemployees.model.Employee;
import pe.isil.isilemployees.service.EmployeeService;
import pe.isil.isilemployees.vm.CompleteEmployee;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //GET
    @GetMapping("/employees")
    public ResponseEntity getAll(){
        List<Employee> employees = employeeService.findAll();
        if(employees == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(employees,HttpStatus.OK);
    }

    //GET BY ID

    @GetMapping("/employees/{id}")
    public ResponseEntity getById(@PathVariable Long id){

        Employee currentEmployee = employeeService.findById(id);
        if(currentEmployee == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(currentEmployee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity create(@RequestBody Employee employee){
        employeeService.create(employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody CompleteEmployee completeEmployee) {

        Employee currentEmployee = employeeService.findById(id);
        if (currentEmployee == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentEmployee.setEmp_id(completeEmployee.getEmployeeId());
        currentEmployee.setName(completeEmployee.getName());


        employeeService.update(currentEmployee);

        return new ResponseEntity(completeEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Employee currentEmployee = employeeService.findById(id);
        if(currentEmployee == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        employeeService.delete(currentEmployee.getEmp_id());
        return new ResponseEntity(HttpStatus.OK);
    }
}

