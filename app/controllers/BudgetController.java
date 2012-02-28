package controllers;

import exceptions.ValidationException;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class BudgetController extends Controller {
	
	public static void create(String name) {
		if(name == null || name.isEmpty()) {
			throw new ValidationException("name", name, "not null / not empty");
		}
		Budget budget = new Budget(name);
		budget.save();
		renderJSON(budget);
	}
	
	public static void get(String id) {
		Budget budget = Budget.findById(id);
		renderJSON(budget);
	}
	
	public static void update(String id) {
		Budget budget = Budget.findById(id);
		if(params._contains("name")) {
			budget.setName(params.get("name"));
		}
	}

	@Catch(ValidationException.class)
	public static void validationException(Throwable throwable) {
		renderJSON(throwable);
	}
}