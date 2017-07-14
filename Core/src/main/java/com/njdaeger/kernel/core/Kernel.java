package com.njdaeger.kernel.core;

import com.njdaeger.kernel.core.server.Player;

import java.util.Collection;
import java.util.UUID;
import java.util.logging.Logger;

public class Kernel {
	
	private static IKernel kernel;
	
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
	
	public static Collection<Player> getPlayers() {
		return kernel.getPlayers();
	}
	
	public static Player getPlayer(String name) {
		return kernel.getPlayer(name);
	}
	
	public static Player getPlayer(UUID userID) {
		return kernel.getPlayer(userID);
	}
	
	public static Logger getLogger() {
		return kernel.getLogger();
	}
}
