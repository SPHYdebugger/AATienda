import DAO.Buy_DAO;
import DAO.ClientDAO;
import DAO.ClientDAO2;
import DAO.Database;
import Domain.Buy;
import Domain.Client;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static DAO.Database.db;
import static DAO.Database.jdbi;

public class Menu {

    private Scanner keyboard;

    public Menu() {
        keyboard = new Scanner(System.in);
        connectToDatabase();
    }

    private void connectToDatabase() {
        Database.connect();
    }

    public void show() throws SQLException {
        boolean salir = false;

        do {
            System.out.println("TIENDA Santi");
            System.out.println("1. Registrar un Cliente");
            System.out.println("2. Modificar un Cliente");
            System.out.println("3. Buscar un Cliente");
            System.out.println("4. Dar de baja un Cliente");
            System.out.println("5. Listar todos los Clientes");
            System.out.println("6. Listar todos las compras");
            System.out.println("7. Registrar una compra");
            System.out.println("8. Ver las compras de un cliente");
            System.out.println("9. Borrar una compra");
            System.out.println("s. Salir");
            String choice = keyboard.nextLine();
            switch (choice) {
                case "1" -> registerClient();
                case "2" -> modifyClient();
                case "3" -> searchClient();
                case "4" -> deleteClient();
                case "5" -> listAllClients();
                case "6" -> listAllBuy();
                case "7" -> registerBuy();
                case "8" -> listClientsBuy();
                case "9" -> deleteBuy();
                case "s" -> {
                    salir = true;
                    Database.close();
                }
            }

        } while (!salir);

        System.out.println("Fin");
    }

    private void registerClient(){
        System.out.print("Nombre: ");
        String firstName = keyboard.nextLine();
        // TODO Comprobar si ya existe un cliente con ese dni
        System.out.print("Apellido: ");
        String lastName = keyboard.nextLine();
        System.out.print("DNI: ");
        String dni = keyboard.nextLine();
        System.out.print("Dirección: ");
        String address = keyboard.nextLine();
        System.out.print("Ciudad: ");
        String city = keyboard.nextLine();
        System.out.print("email: ");
        String email = keyboard.nextLine();
        System.out.print("Password: ");
        String password = keyboard.nextLine();
        System.out.print("Teléfono: ");
        int telephone = keyboard.nextInt();
        keyboard.nextLine();

        jdbi.withExtension(ClientDAO2.class, DAO -> {
            DAO.addClient(firstName, lastName, dni, address, city, email, password, telephone);
            return null;
        });
        System.out.println("El cliente se ha registrado correctamente");
    }

    private void modifyClient(){
        System.out.print("Introduce el DNI del cliente que quieres modificar:");
        String previousDni = keyboard.nextLine();



        int id_client = jdbi.withExtension(ClientDAO2.class, DAO -> {
            Client client = DAO.searchClientByDNI(previousDni);
            return (client != null) ? client.getId_client() : -1; // -1 es un valor de retorno por defecto en caso de que no se encuentre un cliente
        });

        System.out.println("Ese DNI pertenece al cliente con ID " + id_client);
        System.out.println("por favor, introduce los nuevos datos");
        System.out.print("Nombre: ");
        String firstName = keyboard.nextLine();
        // TODO Comprobar si ya existe un cliente con ese dni
        System.out.print("Apellido: ");
        String lastName = keyboard.nextLine();
        System.out.print("DNI: ");
        String dni = keyboard.nextLine();
        System.out.print("Dirección: ");
        String address = keyboard.nextLine();
        System.out.print("Ciudad: ");
        String city = keyboard.nextLine();
        System.out.print("email: ");
        String email = keyboard.nextLine();
        System.out.print("Password: ");
        String password = keyboard.nextLine();
        System.out.print("Teléfono: ");
        int telephone = keyboard.nextInt();
        keyboard.nextLine();

        jdbi.withExtension(ClientDAO2.class, DAO -> {
            DAO.modifyClient(firstName, lastName, dni, address, city, email, password, telephone,id_client);
            return null;
        });
        System.out.println("El cliente se ha registrado correctamente");

    }

    private void searchClient(){
        System.out.print("DNI: ");
        String client_dni = keyboard.nextLine();

        int id_client = jdbi.withExtension(ClientDAO2.class, DAO -> {
            Client client = DAO.searchClientByDNI(client_dni);
            return (client != null) ? client.getId_client() : -1;
        });


        Client client = jdbi.withExtension(ClientDAO2.class, DAO -> DAO.searchClient(id_client));

        System.out.println(client);


    }

    private void deleteClient(){
        System.out.print("DNI: ");
        String dni = keyboard.nextLine();
        jdbi.withExtension(ClientDAO2.class, dao -> {
            dao.deleteClient(dni);
            return null;
        });
    }

    private void listAllClients(){
        List<Client> clients = jdbi.withExtension(ClientDAO2.class, ClientDAO2::getClients);
        clients.forEach(System.out::println);

    }

    private void listAllBuy() {
        List<Buy> buys = jdbi.withExtension(Buy_DAO.class, Buy_DAO::getBuy);
        buys.forEach(System.out::println);
    }

    private void deleteBuy() {
        System.out.print("ID: ");
        int id_buy = keyboard.nextInt();
        jdbi.withExtension(Buy_DAO.class, dao -> {
            dao.deleteBuy(id_buy);
            return null;
        });
    }

    private void registerBuy() {
        System.out.print("ID del cliente: ");
        int id_client = keyboard.nextInt();


        jdbi.withExtension(Buy_DAO.class, DAO -> {
            DAO.addBuy(id_client, LocalDate.now());
            return null;
        });
        System.out.println("El cliente se ha registrado correctamente");
    }

    private void listClientsBuy() {
        System.out.print("DNI: ");
        String client_dni = keyboard.nextLine();

        int id_client = jdbi.withExtension(ClientDAO2.class, DAO -> {
            Client client = DAO.searchClientByDNI(client_dni);
            return (client != null) ? client.getId_client() : -1;
        });


        List<Buy> buys = jdbi.withExtension(Buy_DAO.class, DAO -> DAO.searchClientBuy(id_client));
        buys.forEach(System.out::println);



    }

}
