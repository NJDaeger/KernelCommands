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
	
	/**
	 * Checks if a user has a specific permission.
	 * @param permission The permission to check
	 * @return True if the user has permission, false otherwise.
	 */
	boolean hasPermission(String... permission);
	
	/**
	 * Teleports a player to a new location.
	 * @param location The location to go to.
	 */
	void teleport(Location location);
	
	/**
	 * Force a command upon a user.
	 * @param command The command and its arguments.
	 */
	void sudo(String command);
	
	/**
	 * Sets the user time.
	 * @param time The time to set their time to.
	 */
	void setUserTime(Time time);
	
	/**
	 * Sets the user time.
	 * @param time The time to set their time to.
	 */
	void setUserTime(long time);
	
	/**
	 * Gets the current {@link Time} of the user.
	 *
	 * @return The time closest to the preset times in @{@link Time}
	 */
	Time getUserTime();
	
	/**
	 * Resets the users current time to sync with the server.
	 */
	void resetUserTime();
	
	/**
	 * Sets the user weather.
	 * @param weather What to set their weather to.
	 */
	void setUserWeather(Weather weather);
	
	/**
	 * Gets the current @{@link Weather} of the user.
	 * @return The weather the user currently has.
	 */
	Weather getUserWeather();
	
	/**
	 * Resets the users current weather to sync with the server.
	 */
	void resetUserWeather();
	
	/**
	 * Returns the player represented by this user.
	 * @return The bukkit player version of this user.
	 */
	Player getPlayer();
	
	
}
