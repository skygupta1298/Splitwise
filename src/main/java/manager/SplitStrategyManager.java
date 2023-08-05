package manager;

import enums.ExpenseType;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.ExpenseSplitStrategy;
import strategy.PercentSplitStrategy;

public class SplitStrategyManager {

    public static volatile SplitStrategyManager splitStrategyManager;
    private SplitStrategyManager() {
    }

    public static SplitStrategyManager getInstance() {
        if(splitStrategyManager == null) {
            synchronized (SplitStrategyManager.class) {
                if(splitStrategyManager == null) {
                    splitStrategyManager = new SplitStrategyManager();
                }
            }
        }
        return splitStrategyManager;
    }

    public ExpenseSplitStrategy getSplitStrategy(String expenseType) {
        if(ExpenseType.EQUAL.name().equals(expenseType)) {
            return EqualSplitStrategy.getInstance();
        } else if(ExpenseType.EXACT.name().equals(expenseType)) {
            return ExactSplitStrategy.getInstance();
        } else if(ExpenseType.PERCENT.name().equals(expenseType)) {
            return PercentSplitStrategy.getInstance();
        } else {
            return null;
        }
    }
}
