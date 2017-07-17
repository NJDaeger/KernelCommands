package com.njdaeger.kernel.bukkit;

import com.njdaeger.kernel.core.Permission;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class BukkitPlayer implements com.njdaeger.kernel.core.server.Player {
	
	private final Player player;
	
	public BukkitPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public UUID getUserID() {
		return player.getUniqueId();
	}
	
	@Override
	public String getName() {
		return player.getName();
	}
	
	@Override
	public boolean hasPermission(String permission) {
		return player.hasPermission(permission);
	}
	
	@Override
	public boolean hasPermission(Permission permission) {
		return player.hasPermission(permission.getNode());
	}
	
	@Override
	public void sendMessage(String message) {
		player.sendMessage(message);
	}
	
	@Override
	public void sendMessage(String... message) {
		player.sendMessage(message);
	}
	
	@Override
	public void pluginMessage(String message) {
	
	}
}
