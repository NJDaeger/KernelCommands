package com.njdaeger.kernel.core.server;

import com.njdaeger.kernel.core.Permission;

import java.util.UUID;

public interface Player {
	
	/**
	 * Gets the User UUID
	 * @return The User UUID
	 */
	UUID getUserID();
	
	/**
	 * Checks if the user has permission
	 * @param permission The permission node to look for
	 * @return True if the player has permission
	 */
	boolean hasPermission(String permission);
	
	/**
	 * Checks if the player has a permission node from KernelCommands
	 * @param permission The permission to look for
	 * @return True if the player has permission
	 */
	boolean hasPermission(Permission permission);
	
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
	
	
}
