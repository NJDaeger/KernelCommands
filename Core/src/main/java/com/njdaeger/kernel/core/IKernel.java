package com.njdaeger.kernel.core;

import com.njdaeger.kernel.core.server.Player;

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
	Logger getLogger();
	
}
