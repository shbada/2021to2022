package com.project.springbatch._42_async;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AsyncCustomerRowMapper implements RowMapper<AsyncCustomer> {
    @Override
    public AsyncCustomer mapRow(ResultSet rs, int i) throws SQLException {
        return new AsyncCustomer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthdate"));
    }
}