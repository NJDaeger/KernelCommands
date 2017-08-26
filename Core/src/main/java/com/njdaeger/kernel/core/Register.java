package com.njdaeger.kernel.core;

import com.njdaeger.kernel.core.command.commands.TestCommand;

public class Register {
	
	public static void enable() {
		Kernel.getCommandStore().registerClass(new TestCommand());
	}
	
	public static void disable() {
	
	}
	
}
