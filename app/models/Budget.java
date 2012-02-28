package models;

import org.joda.time.*;

import javax.persistence.*;
import java.util.*;

/**
 * User: joeljohnson
 * Date: 2/11/12
 * Time: 8:32 PM
 */
@Entity
public class Budget extends BaseModel {
	private String name;

	//For persistence
	@ManyToMany	private List<RecurringAmount> dailyIncome;
	@ManyToMany	private List<RecurringAmount> dailyExpenses;

	@ManyToMany	private List<RecurringAmount> weeklyIncome;
	@ManyToMany	private List<RecurringAmount> weeklyExpenses;

	@ManyToMany	private List<RecurringAmount> monthlyIncome;
	@ManyToMany	private List<RecurringAmount> monthlyExpenses;

	@ManyToMany	private List<RecurringAmount> annualIncome;
	@ManyToMany	private List<RecurringAmount> annualExpenses;


	public Budget(String name) {
		this.name = name;

		dailyIncome = new LinkedList<RecurringAmount>();
		dailyExpenses = new LinkedList<RecurringAmount>();

		weeklyIncome = new LinkedList<RecurringAmount>();
		weeklyExpenses = new LinkedList<RecurringAmount>();

		monthlyIncome = new LinkedList<RecurringAmount>();
		monthlyExpenses = new LinkedList<RecurringAmount>();

		annualIncome = new LinkedList<RecurringAmount>();
		annualExpenses = new LinkedList<RecurringAmount>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addIncome(RecurringAmount income) {
		switch (income.getRecurrence()) {
			case DAILY:
				dailyIncome.add(income);
				break;
			case WEEKLY:
				weeklyIncome.add(income);
				break;
			case MONTHLY:
				monthlyIncome.add(income);
				break;
			case ANNUALLY:
				annualIncome.add(income);
				break;
		}
	}

	public void addExpense(RecurringAmount expense) {
		switch (expense.getRecurrence()) {
			case DAILY:
				dailyExpenses.add(expense);
				break;
			case WEEKLY:
				weeklyExpenses.add(expense);
				break;
			case MONTHLY:
				monthlyExpenses.add(expense);
				break;
			case ANNUALLY:
				annualExpenses.add(expense);
				break;
		}
	}

	public double calculate(DateTime startDate, DateTime endDate) {
		double total = 0;
		int days = Days.daysBetween(startDate, endDate).getDays();
		total += totalEarnings(days, dailyIncome, dailyExpenses);

		int weeks = Weeks.weeksBetween(startDate, endDate).getWeeks();
		total += totalEarnings(weeks, weeklyIncome, weeklyExpenses);

		int months = Months.monthsBetween(startDate, endDate).getMonths();
		total += totalEarnings(months, monthlyIncome, monthlyExpenses);

		int years = Years.yearsBetween(startDate, endDate).getYears();
		total += totalEarnings(years, annualIncome, annualExpenses);

		return total;
	}

	private double totalEarnings(int multiplier, List<RecurringAmount> income, List<RecurringAmount> expenses) {
		if(multiplier == 0) return 0;

		int total = 0;
		total += RecurringAmount.sum(income);
		total -= RecurringAmount.sum(expenses);
		total *= multiplier;
		return total;
	}
}
