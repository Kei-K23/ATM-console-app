import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

   public double deposit(int accountNumber, double amount) {
       if (amount < 0) {
           return 0.00;
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

            return checkBalance(accountNumber);

       } catch (SQLException e) {
           // TODO handle exception
           e.printStackTrace();
       }

       // never hit this
       return 0.00;
   }
}
