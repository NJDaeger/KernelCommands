package com.njdaeger.kernel.bukkit.command;

import com.njdaeger.kernel.bukkit.BukkitKernel;
import com.njdaeger.kernel.bukkit.BukkitSender;
import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.command.CommandInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public final class CommandRegister extends Command implements PluginIdentifiableCommand {
	
	private final CommandInfo command;
	private final IKernel kernel;
	
	public CommandRegister(CommandInfo commandInfo, IKernel kernel) {
		super(commandInfo.getName());
		this.command = commandInfo;
		this.kernel = kernel;
		
		this.description = commandInfo.getDesc();
		this.usageMessage = commandInfo.getUsage();
		this.setAliases(Arrays.asList(commandInfo.getAliases()));
		
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		return command.run(new BukkitSender(sender, kernel), args);
	}
	
	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		return command.complete(new BukkitSender(sender, kernel), args);
	}
	
	@Override
	public Plugin getPlugin() {
		return (BukkitKernel)kernel;
	}
}
