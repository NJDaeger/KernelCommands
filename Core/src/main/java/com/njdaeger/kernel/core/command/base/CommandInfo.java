package com.njdaeger.kernel.core.command.base;

import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.Platform;
import com.njdaeger.kernel.core.server.Sender;
import com.njdaeger.kernel.core.server.SenderType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class CommandInfo {
	
	private final int min;
	private final int max;
	private final String name;
	private final String desc;
	private final String usage;
	private final Method method;
	private final String[] aliases;
	private final Object container;
	private final boolean requiresOp;
	private final String[] permissions;
	private CompletionInfo completionInfo;
	private final SenderType[] senderTypes;
	
	public CommandInfo(Object container, Method method) {
		Command command;
		if (!method.isAnnotationPresent(Command.class)) {
			throw new RuntimeException("Cannot find command annotation on method " + method.getName());
		} else command = method.getAnnotation(Command.class);
		this.permissions = command.permissions();
		this.senderTypes = command.executors();
		this.requiresOp = command.needsOp();
		this.aliases = command.aliases();
		this.usage = command.usage();
		this.container = container;
		this.desc = command.desc();
		this.name = command.name();
		this.min = command.min();
		this.max = command.max();
		this.method = method;
		
	}
	
	/**
	 * Gets the minimum amount of arguments allowed to run a command
	 * @return The minimum needed to run a command
	 */
	public int getMin() {
		return min;
	}
	
	/**
	 * Gets the maximum amount of arguments allowed to be in a command
	 * @return The maximum allowed arguments to run a command
	 */
	public int getMax() {
		return max;
	}
	
	/**
	 * Gets the name of the command
	 * @return The command name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the description of the command
	 * @return The command description
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Checks if the command needs to have an operator run this command
	 * @return True if only operators can run this command
	 */
	public boolean needsOp() {
		return requiresOp;
	}
	
	/**
	 * Gets the command usage
	 * @return The command usage
	 */
	public String getUsage() {
		return usage;
	}
	
	/**
	 * Gets all the aliases used to run this command
	 * @return The command aliases
	 */
	public String[] getAliases() {
		return aliases;
	}
	
	/**
	 * Gets all the permissions needed to run this command
	 * @return The permissions needed
	 */
	public String[] getPermissions() {
		return permissions;
	}
	
	/**
	 * Gets the method this command is contained in
	 * @return The command method
	 */
	public Method getMethod() {
		return method;
	}
	
	/**
	 * Gets the tab completion method of this command
	 * @return The tab completion if it exists. Null otherwise
	 */
	public CompletionInfo getCompletionInfo() {
		return completionInfo;
	}
	
	/**
	 * Sets the completion info for this command
	 * @param completionInfo The tab completion info
	 */
	public void setCompletionInfo(CompletionInfo completionInfo) {
		this.completionInfo = completionInfo;
	}
	
	/**
	 * Gets the commands containing object
	 * @return The command container
	 */
	public Object getContainer() {
		return container;
	}
	
	/**
	 * Gets the types of senders allowed to run this command
	 * @return The sender types
	 */
	public SenderType[] getSenderTypes() {
		return senderTypes;
	}
	
	public boolean run(Sender sender, String[] args) {
		
		//Checks the senders permission to run this command
		boolean permission = false;
		if (this.getPermissions().length != 0) {
			for (String node : this.getPermissions()) {
				if (sender.hasPermission(node)) {
					permission = true;
					break;
				}
			}
		} else permission = true;
		
		//Checks the senders type to see if the command is runnable.
		boolean stype = false;
		if (this.getSenderTypes().length != 0) {
			for (SenderType type : this.getSenderTypes()) {
				if (sender.getType().equals(type) || type.equals(SenderType.ALL)) {
					stype = true;
					break;
				}
			}
		} else stype = true;
		
		if (!stype) {
			sender.sendMessage("You cannot run this command.");
			return true;
		}
		if (Kernel.getPlatform() == Platform.BUKKIT && this.needsOp() && !sender.isOp()) {
			sender.sendMessage("Only Operators can run this command.");
			return true;
		}
		if (!permission) {
			sender.sendMessage("You do not have permission to run this command.");
			return true;
		}
		if (this.getMax() < args.length && this.getMax() > -1) {
			sender.sendMessage("Too many args");
			return true;
		}
		if (this.getMin() > args.length && this.getMin() > -1) {
			sender.sendMessage("Not Enough Args");
			return true;
		}
		try {
			this.method.invoke(getContainer(), new CommandContext(sender, args));
		}
		catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public List<String> complete(Sender sender, String[] args) {
		
		List<String> sub = new ArrayList<>();
		
		if (this.completionInfo != null) {
			TabContext context = new TabContext(new CommandContext(sender, args), this, sender, args);
			try {
				this.completionInfo.getMethod().invoke(this.completionInfo.getContainer(), context);
			}
			catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			for (String completion : context.currentPossibleCompletion()) {
				if (completion.toLowerCase().startsWith(context.getCommandContext().argAt(context.getCommandContext().getArgs().size() - 1))) {
					sub.add(completion);
				}
			}
			return sub;
		}
		return null;
	}
	
}
