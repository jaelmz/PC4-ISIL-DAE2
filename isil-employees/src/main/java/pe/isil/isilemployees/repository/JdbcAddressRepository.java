package pe.isil.isilemployees.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pe.isil.isilemployees.model.Address;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcAddressRepository implements AddressRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Address address) {
        final String sql = "insert into tbl_address(country,city, street) values (?,?,?)";
        jdbcTemplate.update(sql, address.getCountry(), address.getCity(), address.getStreet());
    }

    @Override
    public void update(Address address) {
        final String sql = "update tbl_address set country= ? , city= ?, street= ? where add_id = ?";
        jdbcTemplate.update(sql, address.getCountry(), address.getCity(), address.getStreet(), address.getAdd_id());
    }

    @Override
    public void delete(Long add_id) {
        final String sql = "delete tbl_address where add_id = ?";
        jdbcTemplate.update(sql, add_id);
    }

    @Override
    public Address findById(Long add_id) {
        final String sql = "select * from tbl_address where add_id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{add_id},
                JdbcAddressRepository::AddressRowMapper);
    }

    @Override
    public List<Address> findAll() {
        final String sql = "select * from tbl_address";
        return jdbcTemplate.query(sql, JdbcAddressRepository::AddressRowMapper);
    }

    private static Address AddressRowMapper(ResultSet resultSet, int i) throws SQLException {
        Long add_id = resultSet.getLong("add_id");
        String country = resultSet.getString("country");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        return new Address(add_id, country, city, street);
    }

    public Address createAddress(Address address) {
        final String sql = "insert into tbl_address(country, city, street) values (?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, address.getCountry());
                ps.setString(2, address.getCity());
                ps.setString(3, address.getStreet());

                return ps;
            }
        }, holder);

        long newAddressId = holder.getKey().longValue();
        address.setAdd_id(newAddressId);
        return address;
    }
}
