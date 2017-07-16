package com.njdaeger.kernel.core.command;

import com.njdaeger.kernel.core.server.Sender;

public class TabContext {
	
	private final CommandContext context;
	private final Sender sender;
	private final String[] args;
	
	public TabContext(CommandContext context, Sender sender, String[] args) {
		this.context = context;
		this.sender = sender;
		this.args = args;
	}
	
	public CommandContext getCommandContext() {
		return context;
	}
	
	public Sender getSender() {
		return sender;
	}
	
}
