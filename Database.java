import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Database {

    public Account findUserByAccountNumber(int accountNumber) {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();
            Account account = new Account();

            // Example query
            String query = "SELECT * FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 123456);  // Example account number

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
            e.printStackTrace();
        }

        // never hit this
        return null;
    }
}
