import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Clients list definition
        List<Client> listaClientes = new ArrayList<Client>();

        // retrieve the clients from clients.txt
        try {
            Scanner in = new Scanner(new FileReader("C:\\Users\\ca.rojas\\Desktop\\restAssured\\Bank\\src\\clients.txt"));
            StringBuilder sb = new StringBuilder();
            while(in.hasNext()) {
                String name = in.next();
                String username = in.next();
                String password = in.next();
                Float balance = in.nextFloat();
                listaClientes.add(new Client(name, username, password, balance));
            }
            in.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Welcome to globant bank");

        // Define scanner to read user inputs
        Scanner scanner = new Scanner(System.in);
        while (true) {
            interfaz();
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    for (Client cliente: listaClientes ){
                        cliente.mostrarInformacionCliente();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Fullname: ");
                        String nameNewClient = scanner.next();
                        System.out.println("Username: ");
                        String usernameNewClient = scanner.next();
                        System.out.println("password: ");
                        String passwordNewClient = scanner.next();
                        System.out.println("Balance: ");
                        String newBalanceClient = scanner.next();
                        FileWriter myWriter = new FileWriter("src/clients.txt", true);
                        myWriter.write(System.getProperty( "line.separator" ));
                        myWriter.write(nameNewClient +" "+usernameNewClient+" "+passwordNewClient+" "+newBalanceClient);
                        myWriter.close();
                        listaClientes.add(new Client(nameNewClient, usernameNewClient, passwordNewClient, Float.parseFloat(newBalanceClient)));
                        System.out.println("Client succesfully added");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }

    }
    public static void interfaz(){
        System.out.println("What do you want to do?");
        System.out.println("1. See the clients' personal information");
        System.out.println("2. Add a new client");
        System.out.println("3. Sign in and withdraw/transfer");
        System.out.println("5. Close and exit");
    }
}