package com.njdaeger.kernel.craftkernel.session;

import com.njdaeger.kernel.session.LastLocation;
import com.njdaeger.kernel.session.OfflineUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class SimpleLastLocation implements LastLocation {
	
	private final OfflineUser user;
	
	public SimpleLastLocation(OfflineUser user) {
		this.user = user;
	}
	
	@Override
	public double getX() {
		return user.getConfig().getDouble("location.lastx");
	}
	
	@Override
	public double getY() {
		return user.getConfig().getDouble("location.lasty");
	}
	
	@Override
	public double getZ() {
		return user.getConfig().getDouble("location.lastz");
	}
	
	@Override
	public int getYaw() {
		return user.getConfig().getInt("location.lastyaw");
	}
	
	@Override
	public int getPitch() {
		return user.getConfig().getInt("location.lastpitch");
	}
	
	@Override
	public String getWorld() {
		return user.getConfig().getString("location.lastworld");
	}
	
	@Override
	public Location getLocation() {
		return new Location(Bukkit.getWorld(getWorld()), getX(), getY(), getZ(), getYaw(), getPitch());
	}
	
	@Override
	public void setX(double value) {
		user.getConfig().setEntry("location.lastx", value);
	}
	
	@Override
	public void setY(double value) {
		user.getConfig().setEntry("location.lasty", value);
	}
	
	@Override
	public void setZ(double value) {
		user.getConfig().setEntry("location.lastz", value);
	}
	
	@Override
	public void setYaw(float value) {
		user.getConfig().setEntry("location.lastyaw", value);
	}
	
	@Override
	public void setPitch(float value) {
		user.getConfig().setEntry("location.lastpitch", value);
	}
	
	@Override
	public void setWorld(String value) {
		user.getConfig().setEntry("location.lastworld", value);
	}
	
	@Override
	public void setLocation(Location location) {
		setX(location.getX());
		setY(location.getY());
		setZ(location.getZ());
		setYaw(location.getYaw());
		setPitch(location.getPitch());
		setWorld(location.getWorld().getName());
	}
}
