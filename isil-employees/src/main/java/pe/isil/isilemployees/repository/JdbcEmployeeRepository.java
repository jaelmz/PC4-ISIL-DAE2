package pe.isil.isilemployees.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.isil.isilemployees.model.Employee;
import pe.isil.isilemployees.vm.CompleteEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Employee employee) {
        final String sql="insert into tbl_employee(name,addr_id) values(?,?)";
        jdbcTemplate.update(sql, employee.getName(),employee.getAddr_id());

    }

    @Override
    public void update(Employee employee) {
        final String sql = "update tbl_employee set name=?, addr_id=?  where emp_id = ?";
        jdbcTemplate.update(sql, employee.getName(),employee.getAddr_id(),employee.getEmp_id());
    }

    @Override
    public void delete(Long emp_id) {
        final String sql = "delete tbl_employee where emp_id = ?";
        jdbcTemplate.update(sql, emp_id);

    }

    @Override
    public Employee findById(Long emp_id) {
        final String sql = "select * from tbl_employee where emp_id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{emp_id},
                JdbcEmployeeRepository::EmployeeRowMapper);

    }

    @Override
    public List<Employee> findAll() {
        final String sql = "select * from tbl_employee";
        return jdbcTemplate.query(sql,JdbcEmployeeRepository::EmployeeRowMapper);
    }

    private static Employee EmployeeRowMapper(ResultSet resultSet, int i)
        throws SQLException{
        Long emp_id = resultSet.getLong("emp_id");
        String name = resultSet.getString("name");
        Long addr_id = resultSet.getLong("addr_id");
        return new Employee(emp_id,name,addr_id);
    }

    public List<CompleteEmployee> findAllwithAddress(){
        final String sql="SELECT E.EMP_ID, A.ADD_ID," +
                "A.COUNTRY, A.CITY,A.STREET" +
                "FROM TBL_EMPLOYEE E INNER JOIN TBL_ADDRESS A ON" +
                " E.ADDR_ID = A.ADD_ID";

        List<CompleteEmployee> completeEmployees = jdbcTemplate.query(sql,
                JdbcEmployeeRepository::CompleteEmployeeVMRowMapper);
        return completeEmployees;
    }

    private static CompleteEmployee CompleteEmployeeVMRowMapper(ResultSet resultSet, int i) throws SQLException {
        long emp_id = resultSet.getLong("emp_id");
        String name = resultSet.getString("name");
        long add_id = resultSet.getLong("add_id");
        String country = resultSet.getString("country");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        return new CompleteEmployee(emp_id, add_id, name, country, city, street);
    }

    public CompleteEmployee findIdWithAddress(Long id) {
        final String sql = "select e.emp_id, e.name, a.add_id, a.country, a.city, a.street " +
                "from tbl_employee e inner join tbl_address a on e.addr_id = a.add_id " +
                "where e.emp_id = ?";

        CompleteEmployee completeEmployee = null;
        try{
            completeEmployee = jdbcTemplate.queryForObject(sql,
                    new Object[]{id},
                    JdbcEmployeeRepository::CompleteEmployeeVMRowMapper);
        } catch (EmptyResultDataAccessException e){
            System.out.println("e = "+e.getMessage());
        }
        return completeEmployee;
    }
}
