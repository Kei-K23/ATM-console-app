public class Account {
    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double availableBalance; // funds available for withdrawal
    private double totalBalance; // funds available + pending deposits
    private int admin;
    private String username;

    public Account(int accountNumber, int pin, double availableBalance, double totalBalance, int admin,
            String username) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.availableBalance = availableBalance;
        this.totalBalance = totalBalance;
        this.admin = admin;
        this.username = username;
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

    public double getAvailableBalance() {
        return availableBalance;
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

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
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
