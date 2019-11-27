package pe.isil.isilemployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.isil.isilemployees.model.Address;
import pe.isil.isilemployees.repository.JdbcAddressRepository;

import java.util.List;

@Service
public class AddressService implements BaseService<Address,Long> {

    @Autowired
    private JdbcAddressRepository jdbcAddressRepository;

    @Override
    public void create(Address address) {
        jdbcAddressRepository.create(address);
    }

    @Override
    public void update(Address address) {
        jdbcAddressRepository.update(address);
    }

    @Override
    public void delete(Long add_id) {
        jdbcAddressRepository.delete(add_id);
    }

    @Override
    public Address findById(Long add_id) {
        return jdbcAddressRepository.findById(add_id);
    }

    @Override
    public List<Address> findAll() {
        return jdbcAddressRepository.findAll();
    }
}
