import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts = new HashMap<String, Account>();

    public Bank() {
        Account accounts1 = new Account(12345, 12345, 11111, 0, "Customer1");
        Account accounts2 = new Account(12346, 12345, 11111, 0, "Customer2");
        Account accounts3 = new Account(12347, 12345, 11111, 0, "Customer3");
        Account accounts4 = new Account(12348, 12345, 11111, 0, "Customer4");

        accounts.put("12345", accounts1);
        accounts.put("12346", accounts2);
        accounts.put("12347", accounts3);
        accounts.put("12348", accounts4);
    }

    public Account findUserByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
