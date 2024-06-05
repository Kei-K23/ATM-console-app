public class Account {
    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double totalBalance;
    private int admin;
    private String username;

    public Account(int accountNumber, int pin, double totalBalance, int admin,
            String username) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.totalBalance = totalBalance;
        this.admin = admin;
        this.username = username;
    }

    public Account() {

    }

    public boolean isValidate(int pin) {
        if (getPin() == pin) {
            return true;
        } else {
            return false;
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public int getAdmin() {
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
