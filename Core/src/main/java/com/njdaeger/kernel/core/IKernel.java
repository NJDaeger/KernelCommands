package com.njdaeger.kernel.core;

import com.coalesce.core.SenderType;
import com.coalesce.core.command.base.CommandContext;
import com.coalesce.core.plugin.ICoPlugin;
import com.coalesce.core.wrappers.CoSender;
import com.njdaeger.kernel.core.configuration.UserDatabase;
import com.njdaeger.kernel.core.configuration.warps.IWarp;
import com.njdaeger.kernel.core.server.World;
import com.njdaeger.kernel.core.session.OfflineUser;
import com.njdaeger.kernel.core.session.User;

import java.util.Collection;
import java.util.logging.Logger;

public interface IKernel extends ICoPlugin {
    
    /**
     * Gets a collection of all the server worlds
     *
     * @return The server's worlds
     */
    Collection<World> getWorlds();
    
    /**
     * Gets a world via name
     *
     * @param name The name of the world
     * @return The world if it exists.
     */
    World getWorld(String name);
    
    /**
     * Get a collection of users on the server
     *
     * @return The users currently online
     */
    Collection<User> getUsers();
    
    /**
     * Get a user on the server by name
     *
     * @param name The name of the user
     * @return The user, null if they're not online
     */
    User getUser(String name);
    
    /**
     * Gets a user via CoSender
     *
     * @param sender The sender to get the user from
     * @return The user if it exists, or null if the sender isn't a player
     */
    default User getUser(CoSender sender) {
        if (sender.getType() != SenderType.PLAYER) {
            return null;
        }
        return getUser(sender.getName());
    }
    
    /**
     * Gets a user via CommandContext
     *
     * @param context The command context to get the user from
     * @return The user if it exists, or null if the context's sender isn't a player.
     */
    default User getUser(CommandContext context) {
        return getUser(context.getSender());
    }
    
    /**
     * Get an offline user
     *
     * @param name The name of the offline User
     * @return The Offline user, null if the user data doesn't exist.
     */
    OfflineUser getOfflineUser(String name);
    
    /**
     * Get the user database. (Stores pairs of users and their uuid)
     *
     * @return The user database.
     */
    UserDatabase getUserDatabase();
    
    /**
     * Gets all the warps on the entire server
     * @return The server warps
     */
    Collection<IWarp> getWarps();
    
    /**
     * Gets a warp via name
     * @param name The name of the warp.
     * @return The first warp found regardless of the world its in, or if there are duplicates.
     */
    IWarp getWarp(String name);
    
    /**
     * Gets a warp by name and world
     * @param name The name of the warp
     * @param world The name of the world to get the warp from
     * @return The warp. If more warps exist with the same name but on different worlds,
     */
    IWarp getWarp(World world, String name);
    
    /**
     * Gets the Kernel's Logger
     *
     * @return The Kernels logger
     */
    default Logger getLogger() {
        return Logger.getLogger("KernelCommands");
    }
    
}
