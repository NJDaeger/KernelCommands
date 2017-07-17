package com.njdaeger.kernel.core;

public enum Permission {
	
	KERNEL_AFK("kernel.commands.afk"),
	KERNEL_AFK_OTHER("kernel.commands.afk.other"),
	KERNEL_AFK_MESSAGE("kernel.commands.afk.message");
	
	private String node;
	
	Permission(String node) {
		this.node = node;
	}
	
	public String getNode() {
		return node;
	}
	
}
