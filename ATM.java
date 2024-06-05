import java.util.Scanner;

public class ATM {
    private final Bank bank;
    private final Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Enter you ATM Account number");
        final int ACCOUNT_NUMBER = scanner.nextInt();

        Account account = bank.findUserByAccountNumber(ACCOUNT_NUMBER);
        if (account == null) {
            System.out.println("Invalid Account Number");
            return;
        }

        System.out.print("Enter your PIN: ");
        final int PIN = scanner.nextInt();

        if (!account.isValidate(PIN)) {
            System.out.println("Invalid Account PIN");
            return;
        }

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    double totalBalance = bank.checkBalance(ACCOUNT_NUMBER);
                    System.out.println("Total Balance :: " + totalBalance);
                    break;
                case 2:
                    System.out.println("Deposit");
                    System.out.println("How much amount to deposit");
                    double amountForDeposit = scanner.nextDouble();
                    double newBalanceForDeposit = bank.deposit(ACCOUNT_NUMBER, amountForDeposit);
                    System.out.println("New Balance :: " + newBalanceForDeposit);
                    break;
                case 3:
                    System.out.println("Withdraw");
                    System.out.println("How much amount to withdraw");
                    double amountForWithdraw = scanner.nextDouble();
                    double newBalanceForWithdraw = bank.withdraw(ACCOUNT_NUMBER, amountForWithdraw);
                    System.out.println("New Balance :: " + newBalanceForWithdraw);
                    break;
                case 4:
                    System.out.println("Exit");
                    System.out.println("Thank you for using ATM console!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
