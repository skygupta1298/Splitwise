import enums.ExpenseType;
import models.ExpenseMetdata;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ExpenseMetadataParser {

    public static ExpenseMetdata getExpenseMetdata(String input) {
        String[] split = input.split(" ");
        int index = 1;
        int paidByUser = Integer.parseInt(split[index++]);
        double amount = Double.parseDouble(split[index++]);
        int paidForUserCount = Integer.parseInt(split[index++]);
        List<Integer> paidToList = new ArrayList<>();
        for(int i=0; i<paidForUserCount; i++) {
            paidToList.add(Integer.parseInt(split[index++]));
        }
        List<Double> paidValue = new ArrayList<>();
        String expenseType = split[index++];
        if(!expenseType.equals(ExpenseType.EQUAL.name())) {
            for(int i=0; i<paidForUserCount; i++) {
                paidValue.add(Double.parseDouble(split[index++]));
            }
        }
        return ExpenseMetdata.builder()
                .paidToValue(paidValue)
                .createdAt(Instant.now())
                .expenseType(expenseType)
                .paidByUser(paidByUser)
                .paidToUser(paidToList)
                .totalAmountPaid(amount)
                .build();
    }
}
