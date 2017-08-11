package com.njdaeger.kernel.bukkit.command;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.command.base.CommandInfo;
import com.njdaeger.kernel.core.command.base.CommandStore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public final class BukkitCommandStore extends CommandStore {
	
	private final IKernel kernel;
	
	public BukkitCommandStore(IKernel kernel) {
		this.kernel = kernel;
	}
	
	@Override
	public void registerCommand(CommandInfo info) {
		try {
			
			Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			field.setAccessible(true);
			CommandMap map = (CommandMap)field.get(Bukkit.getServer());
			
			if (map == null) {
				throw new RuntimeException("Bukkit CommandMap could not be found");
			}
			
			if (map.getCommand(info.getName()) == null) {
				map.register("kernelcommands", new CommandRegister(info, kernel));
			}
		}
		
		catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isRegistered(String commandName) {
		boolean registered = false;
		try {
			
			Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			field.setAccessible(true);
			CommandMap map = (CommandMap)field.get(Bukkit.getServer());
			
			if (map == null) {
				throw new RuntimeException("Bukkit CommandMap could not be found");
			}
			
			registered = map.getCommand(commandName) != null;
		}
		
		catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return registered;
	}
	
}
