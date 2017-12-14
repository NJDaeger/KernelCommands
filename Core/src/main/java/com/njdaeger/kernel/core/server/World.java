package com.njdaeger.kernel.core.server;

import com.njdaeger.kernel.core.session.User;

import java.util.List;
import java.util.UUID;

public interface World {
    
    /**
     * Gets the name of a world
     *
     * @return The world name
     */
    String getName();
    
    /**
     * Gets the world ID
     *
     * @return The world ID
     */
    UUID getID();
    
    /**
     * Gets a list of all the players in the world.
     *
     * @return All the players in the world
     */
    List<User> getUsers();
    
    
}
