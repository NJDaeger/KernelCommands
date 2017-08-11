package com.njdaeger.kernel.core;

import com.njdaeger.kernel.core.command.base.CommandStore;
import com.njdaeger.kernel.core.server.Player;

import java.io.File;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Logger;

public final class Kernel {
	
	private static IKernel kernel;
	
	/**
	 * Gets the main Kernel
	 * @return The Server neutral Kernel
	 */
	public static IKernel getKernel() {
		return kernel;
	}
	
	/**
	 * Sets the Kernel that is currently in use
	 * @param kernel The kernel to use
	 */
	public static void setKernel(IKernel kernel) {
		if (Kernel.kernel != null) {
			throw new UnsupportedOperationException("The Kernel is already defined.");
		}
		Kernel.kernel = kernel;
	}
	
	/**
	 * All the online players
	 * @return The online players
	 */
	public static Collection<Player> getPlayers() {
		return kernel.getPlayers();
	}
	
	/**
	 * Gets an online player by name
	 * @param name The name of the player
	 * @return The player if online
	 */
	public static Player getPlayer(String name) {
		return kernel.getPlayer(name);
	}
	
	/**
	 * Gets an online player by UUID
	 * @param userID The UserID of the player
	 * @return The player if online
	 */
	public static Player getPlayer(UUID userID) {
		return kernel.getPlayer(userID);
	}
	
	/**
	 * Gets the plugin logger
	 * @return The plugin logger
	 */
	public static Logger getLogger() {
		return kernel.getLogger();
	}
	
	/**
	 * Gets the server platform
	 * @return The server platform
	 */
	 public static Platform getPlatform() {
		return kernel.getPlatform();
	}
	
	/**
	 * Gets the folder holding the mods/plugins
	 * @return The plugins directory
	 */
	public static File getPluginDirectory() {
	 	return kernel.getPluginDirectory();
	}
	
	/**
	 * Gets the plugin command store
	 * @return The command store
	 */
	public static CommandStore getCommandStore() {
		return kernel.getCommandStore();
	}
}
