package com.spring.batch._61_writer_jpa;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<CustomerForm> {
    @Override
    public CustomerForm mapRow(ResultSet rs, int i) throws SQLException {
        return new CustomerForm(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("birthdate"));
    }
}
