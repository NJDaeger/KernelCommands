package com.njdaeger.kernel.core.command;

import com.njdaeger.kernel.core.command.base.Command;
import com.njdaeger.kernel.core.command.base.KernelCommand;
import com.njdaeger.kernel.core.command.base.KernelCompletion;
import com.njdaeger.kernel.core.server.Sender;
import com.njdaeger.kernel.core.server.SenderType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class CommandInfo {
	
	private final int min;
	private final int max;
	private final String name;
	private final String desc;
	private final String usage;
	private final Command command;
	private final String[] aliases;
	private final boolean requiresOp;
	private final String[] permissions;
	private final SenderType[] senderTypes;
	private final KernelCommand kernelCommand;
	private final KernelCompletion kernelCompletion;
	
	public CommandInfo(Method method, KernelCommand kernelCommand, KernelCompletion kernelCompletion) {
		if (!method.isAnnotationPresent(Command.class)) {
			throw new RuntimeException("");
		} else this.command = method.getAnnotation(Command.class);
		this.kernelCompletion = kernelCompletion;
		this.permissions = command.permissions();
		this.senderTypes = command.executors();
		this.requiresOp = command.needsOp();
		this.kernelCommand = kernelCommand;
		this.aliases = command.aliases();
		this.usage = command.usage();
		this.desc = command.desc();
		this.name = command.name();
		this.min = command.min();
		this.max = command.max();
		
	}
	
	public KernelCommand getKernelCommand() {
		return kernelCommand;
	}
	
	public KernelCompletion getKernelCompletion() {
		return kernelCompletion;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public boolean needsOp() {
		return requiresOp;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public Command getCommand() {
		return command;
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public String[] getPermissions() {
		return permissions;
	}
	
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
		if (command.needsOp() && !sender.isOp()) {
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
		this.getKernelCommand().run(new CommandContext(sender, args));
		return true;
	}
	
	public List<String> complete(Sender sender, String[] args) {
		List<String> sub = new ArrayList<>();
		
		if (this.getKernelCompletion() != null) {
			TabContext context = new TabContext(new CommandContext(sender, args), this, sender, args);
			this.getKernelCompletion().complete(context);
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
