import enums.InputType;
import manager.SplitStrategyManager;
import manager.UserManager;
import models.User;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        User user1 = new User(1, "Rahul", "rkgupta1298@gmail.com", "12345678");
        User user2 = new User(2, "Chiti", "chitfund12@gmail.com", "12345678");
        User user3 = new User(3, "Suman", "sumanRoorkee@gmail.com", "12345678");
        User user4 = new User(4, "Bhagi", "bhagiBathini@gmail.com", "12345678");
        UserManager userManager = UserManager.getInstance();
        userManager.addUser(user1);
        userManager.addUser(user2);
        userManager.addUser(user3);
        userManager.addUser(user4);
        SplitwiseApp splitwiseApp = SplitwiseApp.getInstance();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if(input.equals("exit")) {
                break;
            }
            String[] inputSplit = input.split(" ");
            if(InputType.EXPENSE.name().equals(inputSplit[0])) {
                splitwiseApp.addExpense(input);
            } else if(InputType.SHOW_ALL.name().equals(inputSplit[0])) {
                splitwiseApp.showBalanceForAll();
            } else if(InputType.SHOW.name().equals(inputSplit[0])) {
                splitwiseApp.showBalanceForUser(Integer.parseInt(inputSplit[1]));
            } else {
                System.out.println("Invalid Input");
            }
        }
    }
}
