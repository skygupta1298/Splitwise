package manager;

import models.User;

import java.util.*;

public class UserManager {

    public static volatile UserManager userManager;

    private UserManager() {
        this.userMap = new HashMap<>();
    }

    public static UserManager getInstance() {
        if(userManager == null) {
            synchronized (UserManager.class) {
                if(userManager == null) {
                    userManager = new UserManager();
                }
            }
        }
        return userManager;
    }
    private final Map<Integer, User> userMap;

    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    public Optional<User> getUserById(int userId) {
        if(userMap.containsKey(userId)) {
            return Optional.of(userMap.get(userId));
        }
        return Optional.empty();
    }

    public void updateExpenseForUser(int paidBy, int paidTo, double value) {
        User paidByUser = userMap.get(paidBy);
        User paidToUser = userMap.get(paidTo);
        paidByUser.updateBalance(paidTo, value);
        paidToUser.updateBalance(paidBy, -value);
    }

    public Set<Integer> getAllUsers() {
        return userMap.keySet();
    }
}
