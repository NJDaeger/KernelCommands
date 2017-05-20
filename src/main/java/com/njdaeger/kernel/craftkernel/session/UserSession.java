package com.njdaeger.kernel.craftkernel.session;

import com.coalesce.config.yml.YamlConfig;
import com.njdaeger.kernel.Gamemode;
import com.njdaeger.kernel.Time;
import com.njdaeger.kernel.Weather;
import com.njdaeger.kernel.craftkernel.Core;
import com.njdaeger.kernel.session.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class UserSession extends YamlConfig implements User {
	
	//The player represented by this user.
	private final Player player;
	
	public UserSession(Core core, Player player) {
		super("users" + File.separator + player.getUniqueId().toString() + File.separator + "user", core);
		this.player = player;
		setEntry("uuid", player.getUniqueId().toString());
		setEntry("name", player.getName());
		addEntry("nickname", player.getDisplayName());
		setEntry("address",player.getAddress().getAddress().getHostAddress());
		addEntry("muted", false);
		addEntry("spying", false);
		addEntry("god", false);
		addEntry("messageable", true);
		addEntry("afk", false);
		addEntry("teleportable", true);
		addEntry("flyspeed", 1);
		addEntry("walkspeed", 1);
		setEntry("operator", player.isOp());
		setEntry("gamemode", player.getGameMode().name());
		addEntry("hidden", false);
		setEntry("location.loginx", player.getLocation().getBlockX());
		setEntry("location.loginy", player.getLocation().getBlockY());
		setEntry("location.loginz", player.getLocation().getBlockZ());
		setEntry("location.loginyaw", player.getLocation().getYaw());
		setEntry("location.loginpitch", player.getLocation().getPitch());
		setEntry("location.loginworld", player.getWorld().getName());
		addEntry("location.lastx", player.getLocation().getBlockX());
		addEntry("location.lasty", player.getLocation().getBlockY());
		addEntry("location.lastz", player.getLocation().getBlockZ());
		addEntry("location.lastyaw", player.getLocation().getYaw());
		addEntry("location.lastpitch", player.getLocation().getPitch());
		addEntry("location.lastworld", player.getWorld().getName());
		
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public String getIp() {
		return null;
	}
	
	@Override
	public UUID getId() {
		return (UUID) getValue("uuid");
	}
	
	@Override
	public boolean isMuted() {
		return getBoolean("muted");
	}
	
	@Override
	public void setMuted(boolean mute) {
		setEntry("muted", mute);
	}
	
	@Override
	public boolean isSpying() {
		return false;
	}
	
	@Override
	public void setSpying(boolean spying) {
	
	}
	
	@Override
	public boolean isGod() {
		return false;
	}
	
	@Override
	public void setGod(boolean god) {
	
	}
	
	@Override
	public boolean isMessageable() {
		return false;
	}
	
	@Override
	public void setMessageable(boolean messageable) {
	
	}
	
	@Override
	public boolean isAfk() {
		return false;
	}
	
	@Override
	public void setAfk(boolean afk) {
	
	}
	
	@Override
	public boolean isTeleportable() {
		return false;
	}
	
	@Override
	public void setTeleportable(boolean teleportable) {
	
	}
	
	@Override
	public String getNickname() {
		return null;
	}
	
	@Override
	public boolean isNicknamed() {
		return false;
	}
	
	@Override
	public void setNickname(String nickname) {
	
	}
	
	@Override
	public double getFlyingSpeed() {
		return 0;
	}
	
	@Override
	public void setFlyingSpeed(double speed) {
	
	}
	
	@Override
	public double getWalkingSpeed() {
		return 0;
	}
	
	@Override
	public void setWalkingSpeed(double speed) {
	
	}
	
	@Override
	public boolean isOp() {
		return false;
	}
	
	@Override
	public void setOp(boolean op) {
	
	}
	
	@Override
	public Gamemode getGamemode() {
		return null;
	}
	
	@Override
	public void setGamemode(Gamemode gamemode) {
	
	}
	
	@Override
	public void setGamemode(String gamemode) {
	
	}
	
	@Override
	public boolean isHidden() {
		return false;
	}
	
	@Override
	public void setHidden(boolean hidden) {
	
	}
	
	@Override
	public void login() {
	
	}
	
	@Override
	public void logout() {
	
	}
	
	@Override
	public boolean hasPermission(Permission... permission) {
		return false;
	}
	
	@Override
	public boolean hasPermission(String... permission) {
		return false;
	}
	
	@Override
	public void teleport(Location location) {
	
	}
	
	@Override
	public void sudo(String command) {
	
	}
	
	@Override
	public void setUserTime(Time time) {
	
	}
	
	@Override
	public void setUserTime(long time) {
	
	}
	
	@Override
	public Time getUserTime() {
		return null;
	}
	
	@Override
	public void resetUserTime() {
	
	}
	
	@Override
	public void setUserWeather(Weather weather) {
	
	}
	
	@Override
	public Weather getUserWeather() {
		return null;
	}
	
	@Override
	public void resetUserWeather() {
	
	}
}
