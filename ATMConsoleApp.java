import java.util.Scanner;

public class ATMConsoleApp {
    public static void main(String[] args) {
        // Entry point of the program
        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter you ATM PIN");
        final int PIN = scanner.nextInt();

        System.out.println("Enter you ATM password");
        final int PASSWORD = scanner.nextInt();

        System.out.println("PIN : " + PIN + " AND PASSWORD " + PASSWORD);

        scanner.close();
    }
}
