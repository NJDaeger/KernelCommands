package com.njdaeger.kernel.core.configuration.homes;

import com.njdaeger.kernel.core.configuration.Locatable;
import com.njdaeger.kernel.core.configuration.Relocatable;
import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.session.OfflineUser;

public interface IOfflineHome extends Locatable, Relocatable {
    
    String getName();
    
    Location getLocation();
    
    OfflineUser getOwner();
    
    boolean delete();
    
}
