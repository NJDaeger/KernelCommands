package com.njdaeger.kernel.core.configuration.warps;

import com.njdaeger.kernel.core.configuration.Locatable;
import com.njdaeger.kernel.core.configuration.Relocatable;
import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.session.User;

public interface IWarp extends Locatable, Relocatable {
    
    Location getLocation();
    
    String getName();
    
    void sendHere(User user);
    
}
