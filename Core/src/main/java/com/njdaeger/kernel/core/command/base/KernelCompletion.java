package com.njdaeger.kernel.core.command.base;

import com.njdaeger.kernel.core.command.TabContext;

public interface KernelCompletion {
	
	/**
	 * The tab completion method for the command
	 * @param context The tab context
	 */
	void complete(TabContext context);
	
}
