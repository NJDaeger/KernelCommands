package com.njdaeger.kernel.core;

import com.coalesce.core.command.base.CommandContext;
import com.coalesce.core.wrappers.CoSender;
import com.njdaeger.kernel.core.server.World;
import com.njdaeger.kernel.core.session.OfflineUser;
import com.njdaeger.kernel.core.session.User;

import java.util.Collection;
import java.util.logging.Logger;

public final class Kernel {
    
    private static IKernel kernel;
    
    /**
     * Gets the main Kernel
     *
     * @return The Server neutral Kernel
     */
    public static IKernel getKernel() {
        return kernel;
    }
    
    /**
     * Sets the Kernel that is currently in use
     *
     * @param kernel The kernel to use
     */
    public static void setKernel(IKernel kernel) {
        if (Kernel.kernel != null) {
            throw new UnsupportedOperationException("The Kernel is already defined.");
        }
        Kernel.kernel = kernel;
    }
    
    /**
     * Gets the plugin logger
     *
     * @return The plugin logger
     */
    public static Logger getLogger() {
        return kernel.getLogger();
    }
    
    /**
     * Gets a collection of all the server worlds
     *
     * @return The server's worlds
     */
    public static Collection<World> getWorlds() {
        return kernel.getWorlds();
    }
    
    /**
     * Gets a world via name
     *
     * @param name The name of the world
     * @return The world if it exists.
     */
    public static World getWorld(String name) {
        return kernel.getWorld(name);
    }
    
    /**
     * Get a collection of users on the server
     *
     * @return The users currently online
     */
    public static Collection<User> getUsers() {
        return kernel.getUsers();
    }
    
    /**
     * Get a user on the server by name
     *
     * @param name The name of the user
     * @return The user, null if they're not online
     */
    public static User getUser(String name) {
        return kernel.getUser(name);
    }
    
    /**
     * Gets a user via CoSender
     *
     * @param sender The sender to get the user from
     * @return The user if it exists, or null if the sender isn't a player
     */
    public static User getUser(CoSender sender) {
        return kernel.getUser(sender);
    }
    
    /**
     * Gets a user via CommandContext
     *
     * @param context The command context to get the user from
     * @return The user if it exists, or null if the context's sender isn't a player.
     */
    public static User getUser(CommandContext context) {
        return kernel.getUser(context);
    }
    
    /**
     * Get an offline user
     *
     * @param name The name of the offline User
     * @return The Offline user, null if the user data doesn't exist.
     */
    public static OfflineUser getOfflineUser(String name) {
        return kernel.getOfflineUser(name);
    }
    
}
