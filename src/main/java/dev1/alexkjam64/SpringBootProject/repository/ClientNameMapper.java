package dev1.alexkjam64.SpringBootProject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClientNameMapper implements RowMapper<ClientName>{
    
    @Override
    public ClientName mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        return new ClientName(rs.getInt("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("middleInit"));
    }
}
