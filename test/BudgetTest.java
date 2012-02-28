import org.joda.time.DateTime;
import org.junit.*;
import play.test.*;
import models.*;

public class BudgetTest extends UnitTest {
    @Test
    public void expenseCalculation() {
        Budget budget = new Budget("Test Budget");
		budget.addIncome(new RecurringAmount("Adam's Salary", 4000, Recurrence.MONTHLY));
		budget.addIncome(new RecurringAmount("Eve's Salary", 300, Recurrence.WEEKLY));
		budget.addIncome(new RecurringAmount("Bonus", 5000, Recurrence.ANNUALLY));

		budget.addExpense(new RecurringAmount("Mortgage", 1000, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Groceries", 50, Recurrence.WEEKLY));

		double calculatedSavings = budget.calculate(new DateTime(2012, 1, 1, 0, 0, 0, 0), new DateTime(2013, 1, 1, 0, 0, 0, 0));
		assertEquals(budget.getName() + " should have $54,000 remaining after 1 year", 54000, calculatedSavings, 0.0);
    }

	@Test
	public void myExpenses() {
		Budget budget = new Budget("My Budget");
		budget.addIncome(new RecurringAmount("Salary", 4490.00, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("House", 1846.00, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Tithing", 640, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Fast Offering", 50, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Car Insurance", 200, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Groceries", 150, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Emergency", 100, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Life", 50, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Fun", 60, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Gas", 50, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Student Loan A", 189.62, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Student Loan B", 375.03, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Phone", 38, Recurrence.MONTHLY));
		budget.addExpense(new RecurringAmount("Internet", 50, Recurrence.MONTHLY));

		double calculatedSavings = budget.calculate(new DateTime(2012, 1, 1, 0, 0, 0, 0), new DateTime(2013, 1, 1, 0, 0, 0, 0));
		assertEquals("", 8296.20, calculatedSavings, 0.0);
	}
}
/*
(1,846.00)

4,490.00
(640.00)
(50.00)
(200.00)
(150.00)
(100.00)
(50.00)
(60.00)
(50.00)
(189.62)
(375.03)
(38.00)
(50.00)
*/