package com.njdaeger.kernel.core;

import com.njdaeger.kernel.core.modules.homes.HomeCommands;

public class Register {
	
	public static void enable() {
	
		Kernel.getKernel().getCommandStore().registerCommand(new HomeCommands());
	
	}
	
	public static void disable() {
	
	}
	
}
