package com.njdaeger.kernel.core.configuration;

import com.coalesce.core.config.YmlConfig;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.session.User;

import java.util.UUID;

public final class UserDatabase extends YmlConfig {
	
	public UserDatabase() {
		super("userdata", Kernel.getKernel());
	}
	
	/**
	 * Get a user ID via username
	 * @param name the name of the user
	 * @return The user UUID, null if it doesn't exist.
	 */
	public UUID getUserID(String name) {
		if (!contains(name, true)) return null;
		return UUID.fromString(getString(name));
	}
	
	/**
	 * Adds a user to the database
	 * @param user The user to add
	 */
	public void addUser(User user) {
		setEntry(user.getName(), user.getUserID().toString());
	}
	
}
