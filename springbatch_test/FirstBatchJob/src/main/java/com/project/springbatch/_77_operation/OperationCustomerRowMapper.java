package com.project.springbatch._77_operation;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationCustomerRowMapper implements RowMapper<OperationCustomer> {
    @Override
    public OperationCustomer mapRow(ResultSet rs, int i) throws SQLException {
        return new OperationCustomer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthdate"));
    }
}
