package com.project.springbatch._43_multiThread;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MultiThreadCustomerRowMapper implements RowMapper<MultiThreadCustomer> {
    @Override
    public MultiThreadCustomer mapRow(ResultSet rs, int i) throws SQLException {
        return new MultiThreadCustomer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthdate"));
    }
}