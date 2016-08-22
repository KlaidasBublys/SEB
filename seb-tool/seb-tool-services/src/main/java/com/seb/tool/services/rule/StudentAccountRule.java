package com.seb.tool.services.rule;

import com.seb.tool.api.annotation.rule.RuleMethod;
import com.seb.tool.domain.customer.Customer;

public class StudentAccountRule {
	
	private String ruleName = "StudentAccountRule";
	
	@RuleMethod
	public boolean evaluate(Object[] customerContext) {
		if (customerContext[0] instanceof Customer) {
			Customer customer = (Customer) customerContext[0];
			if (customer.isStudent() && customer.getAge() > 17) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
}
