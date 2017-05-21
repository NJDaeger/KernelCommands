package com.njdaeger.kernel.craftkernel.session;

import com.njdaeger.kernel.session.LoginLocation;
import com.njdaeger.kernel.session.OfflineUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class SimpleLoginLocation implements LoginLocation {
	
	private final OfflineUser user;
	
	public SimpleLoginLocation(OfflineUser user) {
		this.user = user;
	}
	
	@Override
	public double getX() {
		return user.getConfig().getDouble("location.loginx");
	}
	
	@Override
	public double getY() {
		return user.getConfig().getDouble("location.loginy");
	}
	
	@Override
	public double getZ() {
		return user.getConfig().getDouble("location.loginz");
	}
	
	@Override
	public int getYaw() {
		return user.getConfig().getInt("location.loginyaw");
	}
	
	@Override
	public int getPitch() {
		return user.getConfig().getInt("location.loginpitch");
	}
	
	@Override
	public String getWorld() {
		return user.getConfig().getString("location.loginworld");
	}
	
	@Override
	public Location getLocation() {
		return new Location(Bukkit.getWorld(getWorld()), getX(), getY(), getZ(), getYaw(), getPitch());
	}
	
	@Override
	public void setX(double value) {
		user.getConfig().setEntry("location.loginx", value);
	}
	
	@Override
	public void setY(double value) {
		user.getConfig().setEntry("location.loginy", value);
	}
	
	@Override
	public void setZ(double value) {
		user.getConfig().setEntry("location.loginz", value);
	}
	
	@Override
	public void setYaw(float value) {
		user.getConfig().setEntry("location.loginyaw", value);
	}
	
	@Override
	public void setPitch(float value) {
		user.getConfig().setEntry("location.loginpitch", value);
	}
	
	@Override
	public void setWorld(String value) {
		user.getConfig().setEntry("location.loginworld", value);
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
