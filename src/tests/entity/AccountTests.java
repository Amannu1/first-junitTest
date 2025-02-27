package tests.entity;

import entities.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import tests.factory.AccountFactory;

public class AccountTests {

    @Test
    public void depositShouldIncreaseBalanceAndDiscountFeeWhenPositiveAmount() {

        double amount = 200.0;
        double expectedValue = 196.0;
        Account acc = AccountFactory.createEmptyAccount();

        acc.deposit(amount);

        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void depositShouldDoNothingWhenNegativeAmount(){

        double expectedValue = 100.0;
        Account acc = AccountFactory.createEmptyAccount(expectedValue);
        double amount = -200.0;

        acc.deposit(amount);

        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void fullWithdrawShouldClearBalanceAndReturnFullBalance(){

        double expectedValue = 0.0;
        double initialValue = 800.0;
        Account acc = AccountFactory.createEmptyAccount(initialValue);

        double result = acc.fullWithdraw();

        Assertions.assertTrue(expectedValue == acc.getBalance());
        Assertions.assertTrue(result == initialValue);
    }

    @Test
    public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {

        Account acc = AccountFactory.createEmptyAccount(800.0);

        acc.withdraw(500);

        Assertions.assertEquals(300.0, acc.getBalance());
    }

    @Test
    public void withdrawShouldThrowExceptionWhenInsufficientBalance(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Account acc = AccountFactory.createEmptyAccount(800.0);

            acc.withdraw(1000.0);
        });
    }
}
