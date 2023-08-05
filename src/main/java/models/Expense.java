package models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Expense {

    private String expenseId;
    private int paidByUser;
    private int paidToUser;
    private double amount;

    public Expense(String expenseId, int paidByUser, int paidToUser, double amount) {
        this.expenseId = expenseId;
        this.paidByUser = paidByUser;
        this.paidToUser = paidToUser;
        this.amount = amount;
    }
}
