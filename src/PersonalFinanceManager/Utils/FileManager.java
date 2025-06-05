package PersonalFinanceManager.Utils;

import PersonalFinanceManager.Models.Transaction;
import PersonalFinanceManager.Models.User;
import PersonalFinanceManager.Models.Budget;
import PersonalFinanceManager.Models.Debt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DATA_DIR = "FinanceManagerData";
    private static final String USERS_FILE = DATA_DIR + "/users.dat";
    private static final String TRANSACTIONS_PREFIX = DATA_DIR + "/transactions_";
    private static final String BUDGETS_PREFIX = DATA_DIR + "/budgets_";
    private static final String DEBTS_PREFIX = DATA_DIR + "/debts_";

    private static User currentUser;

    public static void initializeStorage() {
        try {
            // Create data directory if it doesn't exist( after realizing the directory couldn't be found )
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error creating data directory: " + e.getMessage());
        }
    }

    public static boolean saveUser(User user) {
        List<User> users = loadAllUsers();

        // Check if user already exists
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                // Update existing user (not full implemented thou)
                users.set(i, user);
                return saveAllUsers(users);
            }
        }

        // Add new user
        users.add(user);
        return saveAllUsers(users);
    }

    public static List<User> loadAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            File file = new File(USERS_FILE);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                users = (List<User>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading users: " + e.getMessage());
        }

        return users;
    }

    private static boolean saveAllUsers(List<User> users) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE));
            oos.writeObject(users);
            oos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error saving users: " + e.getMessage());
            return false;
        }
    }

    public static User authenticateUser(String username, String password) {
        List<User> users = loadAllUsers();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                currentUser = user;
                return user;
            }
        }

        return null; // Authentication failed
    }

    public static boolean saveTransactions(List<Transaction> transactions) {
        if (currentUser == null) return false;

        String filename = TRANSACTIONS_PREFIX + currentUser.getUsername() + ".dat";

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(transactions);
            oos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error saving transactions: " + e.getMessage());
            return false;
        }
    }

    public static List<Transaction> loadTransactions() {
        if (currentUser == null) return new ArrayList<>();

        List<Transaction> transactions = new ArrayList<>();
        String filename = TRANSACTIONS_PREFIX + currentUser.getUsername() + ".dat";

        try {
            File file = new File(filename);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                transactions = (List<Transaction>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }

    public static boolean saveBudgets(List<Budget> budgets) {
        if (currentUser == null) return false;

        String filename = BUDGETS_PREFIX + currentUser.getUsername() + ".dat";

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(budgets);
            oos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error saving budgets: " + e.getMessage());
            return false;
        }
    }

    public static List<Budget> loadBudgets() {
        if (currentUser == null) return new ArrayList<>();

        List<Budget> budgets = new ArrayList<>();
        String filename = BUDGETS_PREFIX + currentUser.getUsername() + ".dat";

        try {
            File file = new File(filename);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                budgets = (List<Budget>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading budgets: " + e.getMessage());
        }

        return budgets;
    }

    public static boolean saveDebts(List<Debt> debts) {
        if (currentUser == null) return false;

        String filename = DEBTS_PREFIX + currentUser.getUsername() + ".dat";

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(debts);
            oos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error saving debts: " + e.getMessage());
            return false;
        }
    }

    public static List<Debt> loadDebts() {
        if (currentUser == null) return new ArrayList<>();

        List<Debt> debts = new ArrayList<>();
        String filename = DEBTS_PREFIX + currentUser.getUsername() + ".dat";

        try {
            File file = new File(filename);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                debts = (List<Debt>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading debts: " + e.getMessage());
        }

        return debts;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }
}
