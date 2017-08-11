package com.njdaeger.kernel.core.command.base;

import com.njdaeger.kernel.core.server.Sender;

import java.util.Arrays;
import java.util.List;

public final class CommandContext {

	private final Sender sender;
	private final List<String> args;
	
	public CommandContext(Sender sender, String[] args) {
		this.sender = sender;
		this.args = Arrays.asList(args);
	}
	
	public void pluginMessage(String message) {
		send("");
	}
	
	public List<String> getArgs() {
		return args;
	}
	
	public boolean hasArgs() {
		return !args.isEmpty();
	}
	
	public String argAt(int index) {
		if (index < 0 || index >= args.size()) return null;
		return args.get(index);
	}
	
	public String joinArgs(int start, int finish) {
		if (args.isEmpty()) return "";
		return String.join(" ", args.subList(start, finish));
	}
	
	public String joinArgs(int start) {
		return joinArgs(start, args.size());
	}
	
	public String joinArgs() {
		return joinArgs(0);
	}
	
	public void send(String message) {
		sender.sendMessage(message);
	}

}
