package com.spring.batch._멀티_스레드_프로세싱.partitioning;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int i) throws SQLException {
        return new Customer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthdate"));
    }
}
