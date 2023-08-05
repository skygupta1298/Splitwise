package models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;
@Getter
@Builder
@ToString
public class ExpenseMetdata {

    private final int paidByUser;

    private final List<Integer> paidToUser;

    private final String expenseType;

    private final List<Double> paidToValue;

    private final Double totalAmountPaid;

    private final Instant createdAt;
}
