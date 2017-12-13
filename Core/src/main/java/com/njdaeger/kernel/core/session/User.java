package com.njdaeger.kernel.core.session;

import com.njdaeger.kernel.core.configuration.homes.IOfflineHome;
import com.njdaeger.kernel.core.server.Location;

public interface User extends OfflineUser {
	
	
	@Override
	String getName();
	
	/**
	 * Checks if the user has permission
	 * @param permission The permission node to look for
	 * @return True if the player has permission
	 */
	boolean hasPermission(String permission);
	
	/**
	 * Sends a message to the player
	 * @param message The message to send
	 */
	void sendMessage(String message);
	
	/**
	 * Sends messages to the player. Each line separated
	 * @param message The messages to send
	 */
	void sendMessage(String... message);
	
	/**
	 * Send a plugin message to the player.
	 * @param message The message to send.
	 */
	void pluginMessage(String message);
	
	/**
	 * Teleports a user to a specific location
	 * @param location The location to teleport to
	 */
	void teleportTo(Location location);
	
	/**
	 * Creates a home for this user at their current location.
	 * @param name The name of the home
	 */
	void addHome(String name);
	
	@Override
	Location getLocation();
	
	/**
	 * Loads the user information and adds them into memory
	 */
	void login();
	
	/**
	 * Saves the user information and removes them from memory
	 */
	void logout();
	
	/**
	 * Teleports a user to a home.
	 * @param home The home to teleport the user to.
	 */
	default void teleportHome(IOfflineHome home) {
		teleportTo(home.getLocation());
	}
}
