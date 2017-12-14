package com.njdaeger.kernel.core.server;

import com.njdaeger.kernel.core.IKernel;

public interface Sender {
    
    /**
     * Sends a message to this Sender
     *
     * @param message The message to send
     */
    void sendMessage(String message);
    
    /**
     * Gets the Kernel
     *
     * @return The Kernel
     */
    IKernel getKernel();
    
    /**
     * Gets the name of this sender
     *
     * @return The name of the sender
     */
    String getName();
    
    /**
     * Gets the type of sender this is
     *
     * @return The sender type
     */
    SenderType getType();
    
    /**
     * Checks if the sender has a permission
     *
     * @param permission The permission to look for
     * @return True if the user has permission, false otherwise.
     */
    boolean hasPermission(String permission);
    
}
