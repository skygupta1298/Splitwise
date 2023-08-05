package strategy;

import models.Expense;
import models.ExpenseMetdata;

import java.util.List;

public interface ExpenseSplitStrategy {

    List<Expense> splitExpense(ExpenseMetdata expenseMetdata);
}
