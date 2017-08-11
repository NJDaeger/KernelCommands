package com.njdaeger.kernel.core.command.base;

public interface KernelCompletion {
	
	/**
	 * The tab completion method for the command
	 * @param context The tab context
	 */
	void complete(TabContext context);
	
}
