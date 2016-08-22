package com.seb.tool.services.rule.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.seb.tool.api.rule.Rule;
import com.seb.tool.api.rule.manager.RuleManager;
import com.seb.tool.services.rule.handler.RuleHandler;

@Service
public class RuleManagerImpl implements RuleManager {

	private List<Rule> rules = new ArrayList<>();

	@Override
	public void initRules(List<Object> rules) {
		this.rules = new ArrayList<>();
		for (Object rule : rules) {
			addRule(rule);
		}
	}
	
	@Override
	public boolean executeRules(Object[] rulesContext) {
		boolean isAllPassed = true;
		for (Rule rule : rules) {
			boolean evaluation = rule.evaluate(rulesContext);
			if (evaluation) {
				isAllPassed = true;
			} else {
				isAllPassed = false;
				break;
			}
		}
		// reset all executed rules
		rules = new ArrayList<>();
		return isAllPassed;
	}
	
	private void addRule(Object rule) {
		rules.add(RuleHandler.newInstance(rule));
	}
	
}
