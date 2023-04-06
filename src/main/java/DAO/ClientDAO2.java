package DAO;

import Domain.Client;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.sql.SQLException;
import java.util.List;


public interface ClientDAO2 {



    @SqlQuery("SELECT * FROM clients")
    @UseRowMapper(ClientMapper.class)
    List<Client> getClients();

    @SqlUpdate("INSERT INTO clients (firstname, lastname, DNI, address, city, email, password, telephone)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
    void addClient(String firstName, String lastName, String DNI, String address, String city,
                   String email, String password, int telephone);

    @SqlUpdate("DELETE FROM clients WHERE DNI = ?")
    void deleteClient(String dni);

    @SqlUpdate("UPDATE clients SET firstname = ?, lastname = ?, DNI = ?," +
            "address = ?, city = ?, email = ?, password = ?, telephone = ? " +
            "WHERE ID_CLIENT= ?")
    void modifyClient(String firstName, String lastName, String DNI, String address, String city,
                   String email, String password, int telephone, int id_client);

    @SqlQuery("SELECT * FROM clients WHERE DNI=?")
    @UseRowMapper(ClientMapper.class)
    Client searchClientByDNI(String dni);

    @SqlQuery("SELECT * FROM clients WHERE id_client=?")
    @UseRowMapper(ClientMapper.class)
    Client searchClient(int id_client);





}
