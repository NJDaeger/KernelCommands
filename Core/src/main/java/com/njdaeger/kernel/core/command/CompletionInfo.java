package com.njdaeger.kernel.core.command;

import com.njdaeger.kernel.core.command.base.Completion;

import java.lang.reflect.Method;

public final class CompletionInfo {
	
	private final Completion completion;
	private final String[] commands;
	private final Object container;
	private final Method method;
	
	public CompletionInfo(Completion completion, Method method, Object container) {
		this.commands = completion.commands();
		this.completion = completion;
		this.container = container;
		this.method = method;
	}
	
	
	public Completion getCompletion() {
		return completion;
	}
	
	public String[] getCommands() {
		return commands;
	}
	
	public Object getContainer() {
		return container;
	}
	
	public Method getMethod() {
		return method;
	}
}
