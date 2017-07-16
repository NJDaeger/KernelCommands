package com.njdaeger.kernel.bukkit.command;

import com.njdaeger.kernel.bukkit.BukkitKernel;
import com.njdaeger.kernel.bukkit.BukkitSender;
import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.command.CommandContext;
import com.njdaeger.kernel.core.command.CommandInfo;
import com.njdaeger.kernel.core.command.CompletionInfo;
import com.njdaeger.kernel.core.command.TabContext;
import com.njdaeger.kernel.core.server.Sender;
import com.njdaeger.kernel.core.server.SenderType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class CommandRegister extends Command implements PluginIdentifiableCommand {
	
	private final CompletionInfo completion;
	private final CommandInfo command;
	private final IKernel kernel;
	
	public CommandRegister(CommandInfo commandInfo, CompletionInfo completionInfo, IKernel kernel) {
		super(commandInfo.getName());
		this.completion = completionInfo;
		this.command = commandInfo;
		this.kernel = kernel;
		
		this.description = commandInfo.getDesc();
		this.usageMessage = commandInfo.getUsage();
		this.setAliases(Arrays.asList(commandInfo.getAliases()));
		
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (Arrays.asList(command.getAliases()).contains(commandLabel)) {
			return run(new BukkitSender(sender, kernel), args);
		}
		return false;
	}
	
	private boolean run(Sender sender, String[] args) {
		boolean permission = false;
		for (String node : command.getPermissions()) {
			if (sender.hasPermission(node)) {
				permission = true;
				break;
			}
		}
		boolean stype = false;
		for (SenderType type : command.getSenderTypes()) {
			if (sender.getType().equals(type)) {
				stype = true;
				break;
			}
		}
		if (!stype) return true;
		if (command.needsOp() && !sender.isOp()) return true;
		if (!permission) return true;
		if (command.getMax() < args.length && command.getMax() > -1) return true;
		if (command.getMin() > args.length && command.getMin() > -1) return true;
		command.getKernelCommand().run(new CommandContext(sender, args, kernel));
		return false;
	}
	
	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		return null;
	}
	
	private void complete(Sender sender, String[] args) {
		command.getKernelCompletion().complete(new TabContext());
	}
	
	@Override
	public Plugin getPlugin() {
		return (BukkitKernel)kernel;
	}
}
