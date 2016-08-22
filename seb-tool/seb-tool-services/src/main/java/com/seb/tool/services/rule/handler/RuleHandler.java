package com.seb.tool.services.rule.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.seb.tool.api.annotation.rule.RuleMethod;
import com.seb.tool.api.rule.Rule;

public class RuleHandler implements InvocationHandler {

	private Object rule;
	
	public RuleHandler(final Object rule) {
		this.rule = rule;
	}
	
	public static Rule newInstance(final Object rule) {
		return (Rule) Proxy.newProxyInstance(Rule.class.getClassLoader(), new Class[] {Rule.class}, new RuleHandler(rule));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		if (methodName.equals("evaluate")) {
			return getRuleMethod().invoke(rule, args);
		}
		return null;
	}
	
	private Method getRuleMethod() {
		Method[] methods = rule.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(RuleMethod.class)) {
				return method;
			}
		}
		return null;
	}

}
