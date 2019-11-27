package pe.isil.isilemployees.vm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteEmployee {

    private Long employeeId;
    private Long addressId;
    private String name;
    private String Country;
    private String City;
    private String Street;

}
