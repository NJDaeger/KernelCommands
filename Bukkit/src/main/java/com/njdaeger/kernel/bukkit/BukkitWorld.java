package com.njdaeger.kernel.bukkit;

import com.njdaeger.kernel.core.session.User;
import org.bukkit.World;

import java.util.List;
import java.util.UUID;

public class BukkitWorld implements com.njdaeger.kernel.core.server.World {
	
	private final World world;
	
	public BukkitWorld(World world) {
		this.world = world;
	}
	
	@Override
	public String getName() {
		return world.getName();
	}
	
	@Override
	public UUID getID() {
		return world.getUID();
	}
	
	@Override
	public List<User> getUsers() {
		return null;
	}
}
