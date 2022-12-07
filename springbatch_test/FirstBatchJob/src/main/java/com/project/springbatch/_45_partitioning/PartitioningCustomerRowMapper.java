package com.project.springbatch._45_partitioning;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartitioningCustomerRowMapper implements RowMapper<PartitioningCustomer> {
    @Override
    public PartitioningCustomer mapRow(ResultSet rs, int i) throws SQLException {
        return new PartitioningCustomer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthdate"));
    }
}
