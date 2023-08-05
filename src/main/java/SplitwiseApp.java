import exceptions.UserNotFoundException;
import manager.SplitStrategyManager;
import manager.UserManager;
import models.Expense;
import models.ExpenseMetdata;
import models.User;
import strategy.ExpenseSplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SplitwiseApp {

    public static volatile SplitwiseApp splitwiseApp;
    private SplitwiseApp() {
    }

    public static SplitwiseApp getInstance() {
        if(splitwiseApp == null) {
            synchronized (SplitwiseApp.class) {
                if(splitwiseApp == null) {
                    splitwiseApp = new SplitwiseApp();
                }
            }
        }
        return splitwiseApp;
    }

    public void addExpense(String expenseString) {
        ExpenseMetdata expenseMetdata = ExpenseMetadataParser.getExpenseMetdata(expenseString);
        ExpenseSplitStrategy expenseSplitStrategy = SplitStrategyManager.getInstance().getSplitStrategy(expenseMetdata.getExpenseType());
        List<Expense> expenseList = expenseSplitStrategy.splitExpense(expenseMetdata);
        for(Expense expense : expenseList) {
            UserManager.getInstance().updateExpenseForUser(expense.getPaidByUser(), expense.getPaidToUser(), expense.getAmount());
        }
        System.out.println("Added expense successfully");
    }

    public void showBalanceForUser(int userId) {
        Optional<User> userOptional = UserManager.getInstance().getUserById(userId);
        if(userOptional.isPresent()) {
            printExpenseDetails(userOptional.get());
            System.out.println("Printed balance for user: " + userId);
            return;
        }
        throw new UserNotFoundException("Invalid user " + userId);
    }

    private void printExpenseDetails(User user) {
        for(Map.Entry<Integer, Double> entry : user.getUserBalance().entrySet()) {
            if(entry.getValue() > 0) {
                System.out.printf("User %d owes %f to user %d \n", entry.getKey(), Math.abs(entry.getValue()), user.getUserId());
            } else if(entry.getValue() < 0) {
                System.out.printf("User %d owes %f to user %d \n", user.getUserId(), Math.abs(entry.getValue()), entry.getKey());
            }
        }
    }

    public void showBalanceForAll() {
        Set<Integer> users = UserManager.getInstance().getAllUsers();
        for(Integer userId : users) {
            showBalanceForUser(userId);
        }
        System.out.println("Printed balance for all the users");
    }
}
