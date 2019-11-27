package pe.isil.isilemployees.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.isil.isilemployees.model.Address;
import pe.isil.isilemployees.model.Employee;
import pe.isil.isilemployees.repository.JdbcAddressRepository;
import pe.isil.isilemployees.repository.JdbcEmployeeRepository;
import pe.isil.isilemployees.vm.CompleteEmployee;

import java.util.List;

@Service
public class EmployeeService implements BaseService<Employee,Long> {

    @Autowired
    JdbcEmployeeRepository jdbcEmployeeRepository;

    @Autowired
    JdbcAddressRepository jdbcAddressRepository;

    public EmployeeService(JdbcEmployeeRepository jdbcEmployeeRepository, JdbcAddressRepository jdbcAddressRepository) {
        this.jdbcEmployeeRepository = jdbcEmployeeRepository;
        this.jdbcAddressRepository = jdbcAddressRepository;
    }

    @Override
    public void create(Employee address) {
        jdbcEmployeeRepository.create(address);
    }

    @Override
    public void update(Employee address) {
        jdbcEmployeeRepository.update(address);
    }

    @Override
    public void delete(Long add_id) {
        jdbcEmployeeRepository.delete(add_id);
    }

    @Override
    public Employee findById(Long add_id) {
        return jdbcEmployeeRepository.findById(add_id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcEmployeeRepository.findAll();
    }

    public void createEmployee(CompleteEmployee employeeVM) {

        Address address =
                new Address(null, employeeVM.getCountry(),
                        employeeVM.getCity(), employeeVM.getStreet());

        Address currentAddress = jdbcAddressRepository.createAddress(address);

        Employee employee =
                new Employee(null, employeeVM.getName(),currentAddress.getAdd_id());

        jdbcEmployeeRepository.create(employee);

    }

    public void updateEmployee(CompleteEmployee employeeVM) {

        Employee currentEmployee = jdbcEmployeeRepository.findById(employeeVM.getEmployeeId());
        currentEmployee.setName(employeeVM.getName());
        jdbcEmployeeRepository.update(currentEmployee);

        Address currentAddress = jdbcAddressRepository.findById(currentEmployee.getAddr_id());

        currentAddress.setCountry(employeeVM.getCountry());
        currentAddress.setCity(employeeVM.getCity());
        currentAddress.setStreet(employeeVM.getStreet());
        jdbcAddressRepository.update(currentAddress);
    }

    public void deleteEmployee(Long id) {
        Employee currentEmployee = jdbcEmployeeRepository.findById(id);
        Address currentAddress = jdbcAddressRepository.findById(currentEmployee.getAddr_id());

        jdbcEmployeeRepository.delete(currentEmployee.getEmp_id());
        jdbcAddressRepository.delete(currentAddress.getAdd_id());

    }

    public CompleteEmployee findEmployeeById(Long id) {
        return jdbcEmployeeRepository.findIdWithAddress(id);
    }

    public List<CompleteEmployee> findAllEmployees() {
        return jdbcEmployeeRepository.findAllwithAddress();
    }

}
