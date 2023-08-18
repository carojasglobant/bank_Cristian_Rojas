import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Client {

    // Client information
    String fullName;
    String username;
    String password;

    // Account information
    float balance;
    String accountNumber;

    LocalDate dateofCreation;

    public Client( String fullName, String username, String password){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.dateofCreation = LocalDate.now();
        this.accountNumber = generarCuenta();
    }

    public void mostrarInformacionCliente(){
        System.out.println("Fullname: " + this.fullName);
        System.out.println("Username: " + this.username);
        System.out.println("Account number: " + this.accountNumber);
        System.out.println("Creation date: " + this.dateofCreation);
    }

    public String generarCuenta(){
        String account = "";
        Random randomNumber = new Random();
        for(int i=0; i<10;i++){
            int rand_int = randomNumber.nextInt(10);
            account = account + rand_int;
        }
        return account;
    }
}

