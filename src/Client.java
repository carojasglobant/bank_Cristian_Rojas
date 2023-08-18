import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Client {

    // Client information
    String fullName;
    String username;
    String password;

    // Account information
    Double balance;
    String accountNumber;

    LocalDate dateofCreation;

    public Client( String fullName, String username, String password, Double balance){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.dateofCreation = LocalDate.now();
        this.accountNumber = generarCuenta();
        this.balance = balance;
    }

    public void mostrarInformacionCliente(){
        System.out.println("Fullname: " + this.fullName);
        System.out.println("Username: " + this.username);
        System.out.println("Balance: " + this.balance);
        System.out.println("Account number: " + this.accountNumber);
        System.out.println("Creation date: " + this.dateofCreation + "\n");
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

    public boolean logInIntoAccount(String username, String password){
        return  username.equals(this.username) && password.equals(this.password);
    }

    public String getUsername() {
        return username;
    }

    public void addMoney(Float monto){
        this.balance = this.balance +monto;
    }
    public boolean transferMoney(Float monto){
        if(monto<=this.balance-100){
            this.balance = this.balance - 100 - monto;
            return true;
        } else {
            return false;
        }
    }
    public boolean withDraw(Double monto){
        if(monto  <= this.balance-200 && monto < 1000){
            this.balance = this.balance - monto - 200;
            return true;
        } else if(monto <= this.balance-200-0.15*monto && monto >= 1000){
            this.balance = this.balance-monto-200-0.15*monto;
            return true;
        } else {
            return false;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

