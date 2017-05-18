package com.njdaeger.kernel.session;

import com.njdaeger.kernel.Time;
import com.njdaeger.kernel.Weather;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Represents an online Kernel user.
 */
public interface User extends OfflineUser {
	
	/**
	 * Called when a player logs in.
	 */
	void login();
	
	/**
	 * Called when a player logs out.
	 */
	void logout();
	
	/**
	 * Checks if a user has a specific permission.
	 * @param permission The permission to check
	 * @return True if the user has permission, false otherwise.
	 */
	boolean hasPermission(Permission... permission);
	
	boolean hasPermission(String... permission);
	
	void teleport(Location location);
	
	void sudo(String command);
	
	void setUserTime(Time time);
	
	void setUserTime(long time);
	
	Time getUserTime();
	
	void resetUserTime();
	
	void setUserWeather(Weather weather);
	
	Weather getUserWeather();
	
	void resetUserWeather();
	
	Player getPlayer();
	
	
}
