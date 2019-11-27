package pe.isil.isilemployees.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long emp_id;
    private String name;
    private Long addr_id;
}
