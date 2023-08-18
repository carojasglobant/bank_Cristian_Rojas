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
                Double balance = in.nextDouble();
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
                        listaClientes.add(new Client(nameNewClient, usernameNewClient, passwordNewClient, Double.parseDouble(newBalanceClient)));
                        System.out.println("Client succesfully added");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Username: ");
                    String usernameLogIn = scanner.next();
                    System.out.println("Password: ");
                    String passwordLogIn = scanner.next();
                    int indexOfUser = -1;

                    // encuentro usuario
                    for (int j=0; j<listaClientes.size();j++){
                        if(listaClientes.get(j).getUsername().equals(usernameLogIn)){
                            indexOfUser = j;
                        }
                    }


                    boolean sessionIniciada = true;
                    if(indexOfUser>0 && listaClientes.get(indexOfUser).logInIntoAccount(usernameLogIn,passwordLogIn)){
                        while(sessionIniciada) {
                            interfazLogIn(listaClientes.get(indexOfUser).fullName);
                            int optionLogged = scanner.nextInt();
                            switch (optionLogged){
                                case 1:
                                    System.out.println("How much do you want to add? ");
                                    float montoadd = scanner.nextFloat();
                                    listaClientes.get(indexOfUser).addMoney(montoadd);
                                    System.out.println("Succesful!");
                                    break;
                                case 2:
                                    System.out.println("Account number: ");
                                    String accountNumber = scanner.next();
                                    System.out.println("How much money do you want to transfer?");
                                    Float moneyToTransfer = scanner.nextFloat();

                                    int indexOfUserToTransfer = -1;

                                    // encuentro usuario a transferir
                                    for (int p=0; p<listaClientes.size();p++){
                                        if(listaClientes.get(p).getAccountNumber().equals(accountNumber)){
                                            indexOfUserToTransfer = p;
                                        }
                                    }

                                    if(listaClientes.get(indexOfUser).transferMoney(moneyToTransfer)){
                                        listaClientes.get(indexOfUserToTransfer).addMoney(moneyToTransfer);
                                        System.out.println("Succesful!");
                                    } else {
                                        System.out.println("User not found");
                                    }
                                    break;
                                case 3:
                                    System.out.println("How much money do you want to withdraw");
                                    Double moneyToWithdraw = scanner.nextDouble();
                                    if(listaClientes.get(indexOfUser).withDraw(moneyToWithdraw)){
                                        System.out.println("Successful");
                                    } else {
                                        System.out.println("Insuficciente funds");
                                        System.out.println("Check and try it again");
                                    }
                                    break;
                                case 4:
                                    System.out.println("You are out\n");
                                    sessionIniciada=false;

                                    break;
                                default:
                                    System.out.println("Check again!");
                                    break;

                            }

                        }
                    } else {
                        System.out.println("Wrong user or password");
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Check Again!");
                    break;
            }
        }

    }

    public static void interfaz(){
        System.out.println("What do you want to do?");
        System.out.println("1. See the clients' personal information");
        System.out.println("2. Add a new client");
        System.out.println("3. Sign in and withdraw/transfer");
        System.out.println("4. Close and exit");
    }

    public static void interfazLogIn(String name){
        System.out.println("You are logged as "+name);
        System.out.println("what do you want to do?");
        System.out.println("1. Add money");
        System.out.println("2. Transfer money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Log out");
    }
}