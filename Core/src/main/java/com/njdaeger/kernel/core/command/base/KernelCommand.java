package com.njdaeger.kernel.core.command.base;

@FunctionalInterface
public interface KernelCommand {
	
	/**
	 * Main command run method
	 * @param context The command context
	 *
	 */
	void run(CommandContext context);
	
}
