package com.njdaeger.kernel.core.command;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.server.Player;
import com.njdaeger.kernel.core.server.Sender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TabContext {
	
	private final CommandContext context;
	private final List<String> possible;
	private final CommandInfo command;
	private final Sender sender;
	private final String[] args;
	
	public TabContext(CommandContext context, CommandInfo command, Sender sender, String[] args) {
		this.possible = new ArrayList<>();
		this.command = command;
		this.context = context;
		this.sender = sender;
		this.args = args;
	}
	
	public CommandContext getCommandContext() {
		return context;
	}
	
	public CommandInfo getCommand() {
		return command;
	}
	
	public Sender getSender() {
		return sender;
	}
	
	public int getLength() {
		return args.length - 1;
	}
	
	public boolean length(int length) {
		return getLength() == length;
	}
	
	public String getPrevious() {
		return context.getArgs().get(getLength());
	}
	
	public boolean previous(String previousArg) {
		return getPrevious().matches(previousArg);
	}
	
	public void playerCompletion(int index, Predicate<? super Player> predicate) {
		if (length(index)) {
			possible.clear();
			Kernel.getPlayers().stream().filter(predicate).forEach(p -> possible.add(p.getName()));
		}
	}
	
	public void playerCompletion(int index) {
		if (length(index)) {
			possible.clear();
			Kernel.getPlayers().forEach(p -> possible.add(p.getName()));
		}
	}
	
	public void completion(String... completions) {
		possible.clear();
		possible.addAll(Arrays.asList(completions));
	}
	
	public void completionAt(int index, String... completions) {
		if (length(index)) {
			possible.clear();
			possible.addAll(Arrays.asList(completions));
		}
	}
	
	public void completionAfter(String previousText, String... completions) {
		if (previous(previousText)) {
			possible.clear();
			possible.addAll(Arrays.asList(completions));
		}
	}
	
	public List<String> currentPossibleCompletion() {
		return possible;
	}
	
}
