package be.kuleuven.dbproject;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new User(
                rs.getInt("Customer_ID"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getString("First_Name"),
                rs.getString("Last_Name"),
                rs.getString("Email"),
                rs.getBoolean("Is_Admin"),
                rs.getBoolean("Is_Employee")
        );
    }
}
