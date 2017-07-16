package com.njdaeger.kernel.core.command;

import com.njdaeger.kernel.core.command.base.Command;
import com.njdaeger.kernel.core.command.base.KernelCommand;
import com.njdaeger.kernel.core.command.base.KernelCompletion;
import com.njdaeger.kernel.core.server.SenderType;

import java.lang.reflect.Method;

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
}
