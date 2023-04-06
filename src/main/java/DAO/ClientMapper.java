package DAO;

import Domain.Client;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {


    @Override
    public Client map(ResultSet rs, StatementContext ctx) throws SQLException {

        return new Client(rs.getInt("id_client"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("DNI"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("telephone"));
    }
}
