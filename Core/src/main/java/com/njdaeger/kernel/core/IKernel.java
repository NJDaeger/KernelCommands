package com.njdaeger.kernel.core;

import com.njdaeger.kernel.core.command.base.KernelCommand;
import com.njdaeger.kernel.core.command.base.KernelCompletion;
import com.njdaeger.kernel.core.server.Player;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Logger;

public interface IKernel {
	
	/**
	 * Gets a list of all the online players
	 * @return The online players
	 */
	Collection<Player> getPlayers();
	
	/**
	 * Gets a player via Name
	 * @param name The name of the player to get
	 * @return The player if online
	 */
	Player getPlayer(String name);
	
	/**
	 * Gets a player via UUID
	 * @param userID The UUID of the player
	 * @return The player if online
	 */
	Player getPlayer(UUID userID);
	
	/**
	 * Gets the Kernel's Logger
	 * @return The Kernels logger
	 */
	default Logger getLogger() {
		return Logger.getLogger("KernelCommands");
	}
	
	/**
	 * Registers a command without a tab completion
	 * @param command The command method
	 */
	void addCommand(String methodName, KernelCommand command);
	
	/**
	 * Registers a command with a tab completion
	 * @param command The command method
	 * @param completion The tab completion method
	 */
	void addCommand(String methodName, KernelCommand command, KernelCompletion completion);
	
	/**
	 * Gets the name of this plugin
	 * @return The name of the plugin
	 */
	String getName();
	
	/**
	 * Gets the version number of the plugin
	 * @return The plugin version
	 */
	String getVersion();
	
	/**
	 * Gets the authors of the plugin
	 * @return The plugin authors
	 */
	String getAuthors();
	
	/**
	 * Gets the server platform.
	 * @return The server platform
	 */
	Platform getPlatform();
	
}
