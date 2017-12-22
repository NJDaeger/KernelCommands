package com.njdaeger.kernel.core.server;

import com.njdaeger.kernel.core.configuration.warps.IWarp;
import com.njdaeger.kernel.core.session.User;

import java.util.Collection;
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
     * Gets a collection of all the warps on this world
     * @return The collection of warps on this world.
     */
    Collection<IWarp> getWarps();
    
    /**
     * Gets a list of all the players in the world.
     *
     * @return All the players in the world
     */
    List<User> getUsers();
    
    
}
