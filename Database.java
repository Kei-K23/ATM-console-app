import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {

    static List<Account> accounts = new ArrayList<>();

    public Database() {
        // The original array has been changed into an arraylist, this makes it easier
        // to add/delete from the database
        Account accounts1 = new Account(12345, 12345, 11111, 0, "Customer1");
        Account accounts2 = new Account(12346, 12345, 11111, 0, "Customer2");
        Account accounts3 = new Account(12347, 12345, 11111, 0, "Customer3");
        Account accounts4 = new Account(12348, 12345, 11111, 0, "Customer4");

        accounts.add(accounts1);
        accounts.add(accounts2);
        accounts.add(accounts3);
        accounts.add(accounts4);
    }

    public Account findUserByAccountNumber(int accountNumber) {
        Optional<Account> accountOptional = accounts.stream().filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst();
        return accountOptional.orElse(null);
    }
}
