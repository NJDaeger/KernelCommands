package com.njdaeger.kernel.core.command.base;

import com.njdaeger.kernel.core.command.CommandContext;

@FunctionalInterface
public interface KernelCommand {
	
	/**
	 * Main command run method
	 * @param context The command context
	 *
	 */
	void run(CommandContext context);
	
}
