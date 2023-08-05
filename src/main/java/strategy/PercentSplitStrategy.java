package strategy;

import manager.SplitStrategyManager;
import models.Expense;
import models.ExpenseMetdata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PercentSplitStrategy implements ExpenseSplitStrategy {

    public static volatile PercentSplitStrategy percentSplitStrategy;

    private PercentSplitStrategy() {
    }

    public static PercentSplitStrategy getInstance() {
        if(percentSplitStrategy == null) {
            synchronized (SplitStrategyManager.class) {
                if(percentSplitStrategy == null) {
                    percentSplitStrategy = new PercentSplitStrategy();
                }
            }
        }
        return percentSplitStrategy;
    }

    @Override
    public List<Expense> splitExpense(ExpenseMetdata expenseMetdata) {
        List<Expense> expenses = new ArrayList<>();
        double totalAmount = expenseMetdata.getTotalAmountPaid();
        for(int i=0; i < expenseMetdata.getPaidToUser().size(); i++) {
            double amountOwed = (totalAmount * expenseMetdata.getPaidToValue().get(i)) / 100;
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
