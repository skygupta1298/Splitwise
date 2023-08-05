package models;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class User {

    private final int userId;
    private final String name;
    private final String email;
    private final String mobile;
    private final Map<Integer,Double> userBalance;
    public User(int userId, String name, String email, String mobile) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.userBalance = new HashMap<>();
    }

    public void updateBalance(int userId, double value) {
        if(userBalance.containsKey(userId)) {
            userBalance.put(userId, value + userBalance.get(userId));
        } else {
            userBalance.put(userId, value);
        }
    }
}
// EXPENSE 1 1000 4 1 2 3 4 EQUAL
// EXPENSE 1 1250 2 2 3 EXACT 370 880
// EXPENSE 4 1200 4 1 2 3 4 PERCENT 40 20 20 20