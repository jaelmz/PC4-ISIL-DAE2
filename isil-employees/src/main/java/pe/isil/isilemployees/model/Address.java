package pe.isil.isilemployees.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Long add_id;
    private String country;
    private String city;
    private String street;
}
