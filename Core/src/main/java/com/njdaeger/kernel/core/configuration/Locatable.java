package com.njdaeger.kernel.core.configuration;

import com.njdaeger.kernel.core.server.World;

public interface Locatable {
    
    double getX();
    
    double getY();
    
    double getZ();
    
    float getYaw();
    
    float getPitch();
    
    World getWorld();
    
}
