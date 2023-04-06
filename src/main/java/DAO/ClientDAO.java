package DAO;

import Domain.Client;
import org.jdbi.v3.core.Handle;

import java.sql.SQLException;
import java.util.List;

public class ClientDAO {

    private Handle db;

    public ClientDAO(Handle db) {
        this.db = db;
    }

    public void registerClient(Client cliente) throws SQLException {
        String sql = "INSERT INTO clients (firstname, lastname, DNI, address, city, email, password, telephone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        db.createUpdate(sql)
                .bind(0, cliente.getFirstName())
                .bind(1, cliente.getLastName())
                .bind(2, cliente.getDNI())
                .bind(3, cliente.getAddress())
                .bind(4, cliente.getCity())
                .bind(5, cliente.getEmail())
                .bind(6, cliente.getPassword())
                .bind(7, cliente.getTelephone())

                .execute();
    }

    public boolean modifyClient(String DNI, Client newClientData) throws SQLException {
        String sql = "UPDATE clients SET firstname = ?, lastname = ?, DNI = ?," +
                "address = ? , city = ?, email = ?, password = ?, telephone = ? " +
                "WHERE DNI = ?";
        int count = db.execute(sql,
                newClientData.getFirstName(),
                newClientData.getLastName(),
                newClientData.getDNI(),
                newClientData.getAddress(),
                newClientData.getCity(),
                newClientData.getEmail(),
                newClientData.getPassword(),
                newClientData.getTelephone(),
                DNI);
        return count != 0;
    }

    public boolean deleteClient(String DNI) throws SQLException {
        String sql = "DELETE FROM clients WHERE DNI = ?";
        int count = db.createUpdate(sql)
                .bind(0, DNI)
                .execute();
        return count != 0;
    }

    public List<Client> getAllClients() throws SQLException {
        String sql = "SELECT * FROM clients";
        return db.createQuery(sql)
                .mapToBean(Client.class)
                .list();
    }

    public List<Client> searchClient(String DNI) throws SQLException {
        String sql = "SELECT * FROM clients WHERE DNI = ?";
        return db.createQuery(sql)
                .bind(0, DNI)
                .mapToBean(Client.class)
                .list();
    }

    public boolean isClient(String DNI) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clients WHERE DNI = ?";
        long count = db.createQuery(sql)
                .bind(0, DNI)
                .mapTo(Long.class)
                .one();
        return count != 0;
    }

    public void getClientBuys(String DNI) throws SQLException {




    }

    public Client getClient(String dni) throws SQLException {
        String sql = "SELECT * FROM clients WHERE dni = ?";
        Client client = db.createQuery(sql)
                .bind(0, dni)
                .mapToBean(Client.class)
                .one();
        return client;
    }
    public Client getClient(int id) throws SQLException {
        String sql = "SELECT * FROM clients WHERE id_client = ?";
        Client client = db.createQuery(sql)
                .bind(0, id)
                .mapToBean(Client.class)
                .one();
        return client;
    }
    public Client getClientDni(int id) throws SQLException {
        String sql = "SELECT DNI FROM clients WHERE id_client = ?";
        Client client = db.createQuery(sql)
                .bind(0, id)
                .mapToBean(Client.class)
                .one();
        return client;
    }
}
