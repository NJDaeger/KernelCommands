package com.njdaeger.kernel.core;

import com.njdaeger.kernel.core.command.base.KernelCommand;
import com.njdaeger.kernel.core.command.base.KernelCompletion;
import com.njdaeger.kernel.core.server.Player;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Logger;

public class Kernel {
	
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
	 * Registers a command without a tab completion
	 * @param command The command method
	 */
	public static void addCommand(String methodName, KernelCommand command) {
		kernel.addCommand(methodName, command);
	}
	
	/**
	 * Registers a command with a tab completion
	 * @param command The command method
	 * @param completion The tab completion method
	 */
	public static void addCommand(String methodName, KernelCommand command, KernelCompletion completion) {
		kernel.addCommand(methodName, command, completion);
	}
	
	/**
	 * Gets the server platform
	 * @return The server platform
	 */
	 public static Platform getPlatform() {
		return kernel.getPlatform();
	}
	
}
