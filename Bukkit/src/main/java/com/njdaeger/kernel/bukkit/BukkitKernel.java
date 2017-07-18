package com.njdaeger.kernel.bukkit;

import com.njdaeger.kernel.bukkit.command.CommandRegister;
import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.Platform;
import com.njdaeger.kernel.core.command.CommandContext;
import com.njdaeger.kernel.core.command.CommandInfo;
import com.njdaeger.kernel.core.command.base.KernelCommand;
import com.njdaeger.kernel.core.command.base.KernelCompletion;
import com.njdaeger.kernel.core.command.commands.TestCommand;
import com.njdaeger.kernel.core.server.Player;
import com.njdaeger.kernel.core.server.World;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BukkitKernel extends JavaPlugin implements IKernel, Listener {
	
	private final Map<String, Player> players = new HashMap<>();
	private final Map<String, World> worlds = new HashMap<>();
	
	@Override
	public void onEnable() {
		Kernel.setKernel(this);
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getWorlds().forEach(w -> worlds.put(w.getName(), new BukkitWorld(w)));
		Bukkit.getOnlinePlayers().forEach(p -> players.put(p.getName(), new BukkitPlayer(p)));
		new TestCommand(this);
	}
	
	@Override
	public void onDisable() {
		players.clear();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		players.put(e.getPlayer().getName(), new BukkitPlayer(e.getPlayer()));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		players.remove(e.getPlayer().getName());
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return players.values();
	}
	
	@Override
	public Player getPlayer(String name) {
		Validate.notNull(name, "Player name cannot be null");
		return players.get(name);
	}
	
	@Override
	public Player getPlayer(UUID userID) {
		Validate.notNull(userID, "Player UUID cannot be null");
		return null;
	}
	
	@Override
	public void addCommand(String methodName, KernelCommand command) {
		CommandInfo info = null;
		CommandMap map = null;
		Method method;
		Field field;
		
		try {
			method = Class.forName(command.getClass().getCanonicalName().split("\\$\\$")[0]).getMethod(methodName, CommandContext.class);
			field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			field.setAccessible(true);
			map = (CommandMap)field.get(Bukkit.getServer());
			info = new CommandInfo(method, command, null);
		}
		catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		if (map == null) {
			throw new RuntimeException("Bukkit CommandMap could not be found");
		}
		if (map.getCommand(info.getName()) == null) {
			map.register(getName(), new CommandRegister(info, this));
		}
	}
	
	@Override
	public void addCommand(String methodName, KernelCommand command, KernelCompletion completion) {
		CommandInfo info = null;
		CommandMap map = null;
		Method method;
		Field field;
		
		try {
			method = Class.forName(command.getClass().getCanonicalName().split("\\$\\$")[0]).getMethod(methodName, CommandContext.class);
			field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			field.setAccessible(true);
			map = (CommandMap)field.get(Bukkit.getServer());
			info = new CommandInfo(method, command, completion);
		}
		catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		if (map == null) {
			throw new RuntimeException("Bukkit CommandMap could not be found");
		}
		if (map.getCommand(info.getName()) == null) {
			map.register(getName(), new CommandRegister(info, this));
		}
	}
	
	@Override
	public String getVersion() {
		return getDescription().getVersion();
	}
	
	@Override
	public String getAuthors() {
		return Arrays.toString(getDescription().getAuthors().toArray());
	}
	
	@Override
	public Platform getPlatform() {
		return Platform.BUKKIT;
	}
}
