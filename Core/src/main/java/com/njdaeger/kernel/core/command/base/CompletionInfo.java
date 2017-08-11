package com.njdaeger.kernel.core.command.base;

import java.lang.reflect.Method;

public class CompletionInfo {
	
	private final Method method;
	private final Object container;
	private final String[] commands;
	
	public CompletionInfo(Object container, Method method) {
		Completion completion;
		if (!method.isAnnotationPresent(Completion.class)) {
			throw new RuntimeException("Cannot find completion annotation on method " + method.getName());
		} else completion = method.getAnnotation(Completion.class);
		this.commands = completion.commands();
		this.container = container;
		this.method = method;
	}
	
	/**
	 * Gets all the commands this completion is for
	 * @return The commands
	 */
	public String[] getCommands() {
		return commands;
	}
	
	/**
	 * Gets the method represented by this completion
	 * @return The completion method.
	 */
	public Method getMethod() {
		return method;
	}
	
	/**
	 * The object that hold this completion info
	 * @return The containing object
	 */
	public Object getContainer() {
		return container;
	}
	
}
