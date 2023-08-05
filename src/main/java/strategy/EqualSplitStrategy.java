package strategy;

import manager.SplitStrategyManager;
import models.Expense;
import models.ExpenseMetdata;

import java.util.*;

public class EqualSplitStrategy implements ExpenseSplitStrategy {

    public static volatile EqualSplitStrategy equalSplitStrategy;

    private EqualSplitStrategy() {
    }

    public static EqualSplitStrategy getInstance() {
        if(equalSplitStrategy == null) {
            synchronized (SplitStrategyManager.class) {
                if(equalSplitStrategy == null) {
                    equalSplitStrategy = new EqualSplitStrategy();
                }
            }
        }
        return equalSplitStrategy;
    }

    @Override
    public List<Expense> splitExpense(ExpenseMetdata expenseMetdata) {
        List<Expense> expenses = new ArrayList<>();
        double amountOwed = expenseMetdata.getTotalAmountPaid() / expenseMetdata.getPaidToUser().size();
        for(Integer paidToUser : expenseMetdata.getPaidToUser()) {
            Expense expense = Expense.builder()
                    .expenseId(UUID.randomUUID().toString())
                    .paidToUser(paidToUser)
                    .amount(amountOwed)
                    .paidByUser(expenseMetdata.getPaidByUser())
                    .build();
            expenses.add(expense);
        }
        return expenses;
    }
}
