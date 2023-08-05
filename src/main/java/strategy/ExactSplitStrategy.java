package strategy;

import manager.SplitStrategyManager;
import models.Expense;
import models.ExpenseMetdata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExactSplitStrategy implements ExpenseSplitStrategy {

    public static volatile ExactSplitStrategy exactSplitStrategy;

    private ExactSplitStrategy() {
    }

    public static ExactSplitStrategy getInstance() {
        if(exactSplitStrategy == null) {
            synchronized (SplitStrategyManager.class) {
                if(exactSplitStrategy == null) {
                    exactSplitStrategy = new ExactSplitStrategy();
                }
            }
        }
        return exactSplitStrategy;
    }
    @Override
    public List<Expense> splitExpense(ExpenseMetdata expenseMetdata) {
        List<Expense> expenses = new ArrayList<>();
        for(int i=0; i < expenseMetdata.getPaidToUser().size(); i++) {
            double amountOwed = expenseMetdata.getPaidToValue().get(i);
            Expense expense = Expense.builder()
                    .expenseId(UUID.randomUUID().toString())
                    .paidToUser(expenseMetdata.getPaidToUser().get(i))
                    .amount(amountOwed)
                    .paidByUser(expenseMetdata.getPaidByUser())
                    .build();
            expenses.add(expense);
        }
        return expenses;
    }
}
