package com.njdaeger.kernel.bukkit;

import com.njdaeger.kernel.bukkit.command.BukkitCommandStore;
import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.Platform;
import com.njdaeger.kernel.core.Register;
import com.njdaeger.kernel.core.command.base.CommandStore;
import com.njdaeger.kernel.core.server.Player;
import com.njdaeger.kernel.core.server.World;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BukkitKernel extends JavaPlugin implements IKernel, Listener {
	
	private static final Map<String, Player> PLAYERS;
	private static final Map<String, World> WORLDS;
	private static final File PLUGIN_DIR;
	private CommandStore commandStore;
	
	static {
		PLUGIN_DIR = new File("plugins");
		PLAYERS = new HashMap<>();
		WORLDS = new HashMap<>();
	}
	
	@Override
	public void onEnable() {
		Kernel.setKernel(this);
		commandStore = new BukkitCommandStore(this);
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getWorlds().forEach(w -> WORLDS.put(w.getName(), new BukkitWorld(w)));
		Bukkit.getOnlinePlayers().forEach(p -> PLAYERS.put(p.getName(), new BukkitPlayer(p)));
		Register.enable();
		getCommandStore().updateMaps();
	}
	
	@Override
	public void onDisable() {
		Register.disable();
		PLAYERS.clear();
		WORLDS.clear();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		PLAYERS.put(e.getPlayer().getName(), new BukkitPlayer(e.getPlayer()));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		PLAYERS.remove(e.getPlayer().getName());
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return PLAYERS.values();
	}
	
	@Override
	public Player getPlayer(String name) {
		Validate.notNull(name, "Player name cannot be null");
		return PLAYERS.get(name);
	}
	
	@Override
	public Player getPlayer(UUID userID) {
		Validate.notNull(userID, "Player UUID cannot be null");
		return null;
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
	
	@Override
	public File getPluginDirectory() {
		return PLUGIN_DIR;
	}
	
	@Override
	public CommandStore getCommandStore() {
		return commandStore;
	}
}
