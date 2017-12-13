package com.njdaeger.kernel.core.session;

import com.njdaeger.kernel.core.configuration.UserFile;
import com.njdaeger.kernel.core.configuration.homes.IOfflineHome;
import com.njdaeger.kernel.core.server.Location;

import java.util.List;
import java.util.UUID;

public interface OfflineUser {
	
	/**
	 * Gets the name of the user.
	 * @return The user's name.
	 */
	String getName();
	
	/**
	 * Gets a home from a user
	 * @param name the name of the home
	 * @return The home if it exists
	 */
	IOfflineHome getHome(String name);
	
	/**
	 * Creates a new home for this user
	 * @param name The user to create the home for
	 */
	void addHome(Location location, String name);
	
	/**
	 * Gets a list of this users homes
	 * @return The homes if they have any
	 */
	List<? extends IOfflineHome> getHomes();
	
	/**
	 * Gets the User UUID
	 * @return The User UUID
	 */
	UUID getUserID();
	
	/**
	 * Gets the user configuration file
	 * @return The user config file
	 */
	UserFile getUserFile();
	
	/**
	 * Gets the users location. (or last location depending if the user is online or not)
	 * @return The users current (or last) location
	 */
	Location getLocation();

}
