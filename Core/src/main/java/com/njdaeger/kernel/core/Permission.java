package com.njdaeger.kernel.core;

public final class Permission {
	
	private static final String KC = "kernel.commands.";
	
	public static final String KERNEL_HOME = KC + "home";
	public static final String KERNEL_HOME_ME_OTHER = KC + "home.self-to-other";
	public static final String KERNEL_HOME_OTHER_ME = KC + "home.other-to-self";
	public static final String KERNEL_HOME_OTHER_OWN = KC + "home.other-to-own";
	public static final String KERNEL_HOME_OTHER_OTHER = KC + "home.other-to-other";
	
	public static final String KERNEL_SETHOME = KC + "sethome";
	public static final String KERNEL_SETHOME_OTHER = KC + "sethome.other";
	public static final String KERNEL_SETHOME_OFFLINE = KC + "sethome.offline";
	
	public static final String KERNEL_LISTHOMES = KC + "listhomes";
	public static final String KERNEL_LISTHOMES_OTHER = KC + "listhomes.other";
	public static final String KERNEL_LISTHOMES_OFFLINE = KC + "listhomes.offline";
	
	public static final String KERNEL_DELHOME = KC + "delhome";
	public static final String KERNEL_DELHOME_OTHER = KC + "delhome.other";
	public static final String KERNEL_DELHOME_OFFLINE = KC + "delhome.offline";
	
	public static final String KERNEL_AFK = KC + "afk";
	public static final String KERNEL_AFK_OTHER = KC + "afk.other";
	public static final String KERNEL_AFK_MESSAGE = KC + "afk.message";
	
	private String node;
	
	Permission(String node) {
		this.node = node;
	}
	
	public String getNode() {
		return node;
	}
	
	@Override
	public String toString() {
		return node;
	}
}
