import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank {
    public Account findUserByAccountNumber(int accountNumber) {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();
            Account account = new Account();

            // Example query
            String query = "SELECT * FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountNumber);  // Example account number

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account.setAccountNumber(resultSet.getInt("account_number"));
                account.setPin(resultSet.getInt("pin"));
                account.setTotalBalance(resultSet.getDouble("total_balance"));
                account.setAdmin(resultSet.getInt("admin"));
                account.setUsername(resultSet.getString("username"));
            }

            resultSet.close();
            preparedStatement.close();

            return account;
        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
        }

        // never hit this
        return null;
    }

    public double checkBalance(int accountNumber) {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();
            Account account = new Account();

            // Example query
            String query = "SELECT total_balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountNumber);  // Example account number

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account.setTotalBalance(resultSet.getDouble("total_balance"));
            }

            resultSet.close();
            preparedStatement.close();

            return account.getTotalBalance();
        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
        }

        // never hit this
        return 0.00;
    }

    public void deposit(int accountNumber, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("insufficient amount");
        }
        try {
            double totalBalance = checkBalance(accountNumber);

            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();

            // Example query
            String query = "UPDATE accounts SET total_balance = ? WHERE account_number = ?";
            PreparedStatement updateStatement = connection.prepareStatement(query);
            updateStatement.setDouble(1, totalBalance + amount);
            updateStatement.setInt(2, accountNumber);

            updateStatement.executeUpdate();
            updateStatement.close();
            System.out.println("New Balance :: " + (totalBalance + amount));
        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
        }
    }

    public void withdraw(int accountNumber, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("insufficient amount");
        }
        try {
            double totalBalance = checkBalance(accountNumber);

            if (amount > totalBalance) {
                throw new IllegalArgumentException("insufficient amount");
            }

            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();

            // Example query
            String query = "UPDATE accounts SET total_balance = ? WHERE account_number = ?";
            PreparedStatement updateStatement = connection.prepareStatement(query);
            updateStatement.setDouble(1, totalBalance - amount);
            updateStatement.setInt(2, accountNumber);

            updateStatement.executeUpdate();
            updateStatement.close();

            System.out.println("New Balance :: " + (totalBalance - amount));
        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
        }
    }

    public void transfer(int fromAccountNumber, int toAccountNumber, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("insufficient amount");
        }
        try {
            double totalBalanceForFromAccount = checkBalance(fromAccountNumber);
            double totalBalanceForToAccount = checkBalance(toAccountNumber);

            if (amount > totalBalanceForFromAccount) {
                throw new IllegalArgumentException("insufficient amount");
            }

            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();

            // Example query
            String updateQueryForToAccountNumber = "UPDATE accounts SET total_balance = ? WHERE account_number = ?";
            PreparedStatement updateStatementForToAccountNumber = connection.prepareStatement(updateQueryForToAccountNumber);
            updateStatementForToAccountNumber.setDouble(1, totalBalanceForToAccount + amount);
            updateStatementForToAccountNumber.setInt(2, toAccountNumber);

            updateStatementForToAccountNumber.executeUpdate();
            updateStatementForToAccountNumber.close();// Example query

            String updateQueryForFromAccountNumber = "UPDATE accounts SET total_balance = ? WHERE account_number = ?";
            PreparedStatement updateStatementForFromAccountNumber = connection.prepareStatement(updateQueryForFromAccountNumber);
            updateStatementForFromAccountNumber.setDouble(1, totalBalanceForFromAccount - amount);
            updateStatementForFromAccountNumber.setInt(2, fromAccountNumber);

            updateStatementForFromAccountNumber.executeUpdate();
            updateStatementForFromAccountNumber.close();

            System.out.println(fromAccountNumber + " account have :: " + (totalBalanceForFromAccount - amount) + " total balance after transfer to " + toAccountNumber);
            System.out.println(toAccountNumber + " account have :: " + (totalBalanceForToAccount + amount) + " total balance after received from " + fromAccountNumber);
        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
        }
    }
}
