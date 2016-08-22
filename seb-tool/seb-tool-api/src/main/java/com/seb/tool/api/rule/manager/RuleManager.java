package com.seb.tool.api.rule.manager;

import java.util.List;

public interface RuleManager {
	
	void initRules(List<Object> rules);
	
	boolean executeRules(Object[] rulesContext);
	
}
