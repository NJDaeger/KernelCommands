package com.njdaeger.kernel.bukkit;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.server.Player;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class BukkitKernel extends JavaPlugin implements IKernel, Listener {
	
	private final Map<String, Player> players = new HashMap<>();
	private final Logger logger = Logger.getLogger("KernelCommands");
	
	@Override
	public void onEnable() {
		Kernel.setKernel(this);
		Bukkit.getWorlds().forEach(BukkitWorld::new);
		Bukkit.getOnlinePlayers().forEach(BukkitPlayer::new);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
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
}
