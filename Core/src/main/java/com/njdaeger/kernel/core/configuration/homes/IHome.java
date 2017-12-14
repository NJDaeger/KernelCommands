package com.njdaeger.kernel.core.configuration.homes;

import com.njdaeger.kernel.core.session.User;

public interface IHome extends IOfflineHome {
    
    /**
     * Sends the owner of this home to this home
     */
    void sendHere();
    
    /**
     * Sends another user to this home.
     *
     * @param user The user to send to this home.
     */
    void sendOtherHere(User user);
    
}
