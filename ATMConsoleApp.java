import java.util.Scanner;

public class ATMConsoleApp {
    public static void main(String[] args) {
        // Entry point of the program
        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter you ATM Account number");
        final int ACCOUNT_NUMBER = scanner.nextInt();

        System.out.println("Enter you ATM PIN");
        final int PIN = scanner.nextInt();

        System.out.println("PIN : " + ACCOUNT_NUMBER + " AND PASSWORD " + PIN);

        scanner.close();

        Database database = new Database();
        Account account = database.findUserByAccountNumber(ACCOUNT_NUMBER);
        System.out.println(account.getAccountNumber() + account.getUsername());
    }
}
