package com.seb.tool.services.rule;

import java.util.List;

import com.seb.tool.api.annotation.rule.RuleMethod;
import com.seb.tool.domain.product.Product;

public class DebitCard {
	
	private String ruleName = "DebitCard";
	
	@RuleMethod
	@SuppressWarnings("unchecked")
	public boolean evaluate(Object[] ruleContext) {
		if (ruleContext[0] instanceof List) {
			List<Product> products = (List<Product>) ruleContext[0];
			boolean isAccountExist = false;
			for (Product product : products) {
				if (product.isAccount()) {
					isAccountExist = true;
					break;
				} else {
					isAccountExist = false;
				}
			}
			return isAccountExist;
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
