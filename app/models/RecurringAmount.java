package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;

/**
 * User: joeljohnson
 * Date: 2/11/12
 * Time: 8:32 PM
 */
@Entity
public class RecurringAmount extends BaseModel {
	private final String name;
	private final double amount;

	@Enumerated(EnumType.STRING)
	private final Recurrence recurrence;

	public RecurringAmount(String name, double amount, Recurrence recurrence) {
		this.name = name;
		this.amount = amount;
		this.recurrence = recurrence;
	}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

	public Recurrence getRecurrence() {
		return recurrence;
	}

	public static double sum(Collection<RecurringAmount> amounts) {
		return sum(amounts, 1);
	}

	public static double sum(Collection<RecurringAmount> amounts, int multiplier) {
		long total = 0;
		for (RecurringAmount amount : amounts) {
			total += (amount.getAmount() * multiplier);
		}
		return total;
	}

	@Override
	public String toString() {
		return "RecurringAmount{" +
				"name='" + name + '\'' +
				", amount=" + amount +
				", recurrence=" + recurrence +
				'}';
	}
}
