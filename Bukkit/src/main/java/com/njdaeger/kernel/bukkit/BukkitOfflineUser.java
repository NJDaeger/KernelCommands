package com.njdaeger.kernel.bukkit;

import com.njdaeger.kernel.core.configuration.UserFile;
import com.njdaeger.kernel.core.configuration.homes.IOfflineHome;
import com.njdaeger.kernel.core.configuration.homes.OfflineHome;
import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.session.OfflineUser;

import java.util.List;
import java.util.UUID;

public class BukkitOfflineUser implements OfflineUser {
	
	private final String name;
	private final UUID userID;
	private final UserFile userFile;
	
	public BukkitOfflineUser(String name, UUID userID) {
		this.name = name;
		this.userID = userID;
		this.userFile = new UserFile(this);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public IOfflineHome getHome(String name) {
		return null;
	}
	
	@Override
	public void addHome(Location location, String name) {
		new OfflineHome(this, location, name);
	}
	
	@Override
	public List<IOfflineHome> getHomes() {
		return null;
	}
	
	@Override
	public UUID getUserID() {
		return userID;
	}
	
	@Override
	public UserFile getUserFile() {
		return userFile;
	}
	
	@Override
	public Location getLocation() {
		return null;
	}
}
