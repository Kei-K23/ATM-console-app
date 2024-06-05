public class ATMConsoleApp {
    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm = new ATM(bank);

        // run atm application
        atm.run();
    }
}
