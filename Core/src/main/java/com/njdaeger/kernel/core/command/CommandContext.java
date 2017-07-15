package com.njdaeger.kernel.core.command;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.server.Sender;

import java.util.Arrays;
import java.util.List;

public final class CommandContext {

	private final Sender sender;
	private final IKernel kernel;
	private final List<String> args;
	
	public CommandContext(Sender sender, String[] args, IKernel kernel) {
		this.sender = sender;
		this.kernel = kernel;
		this.args = Arrays.asList(args);
	}
	
	public IKernel getKernel() {
		return kernel;
	}
	
	public void pluginMessage(String message) {
		sender.sendMessage("");
	}
	
	public List<String> getArgs() {
		return null;
	}
	
	public boolean hasArgs() {
		return false;
	}
	
	public String argAt(int index) {
		return null;
	}
	
	public String joinArgs(int start, int finish) {
		return null;
	}
	
	public String joinArgs(int start) {
		return null;
	}
	
	public String joinArgs() {
		return null;
	}
	
	public void send(String message) {
	}

}
