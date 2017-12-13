package com.njdaeger.kernel.core.configuration;

import com.njdaeger.kernel.core.server.World;

public interface Relocatable {
	
	void setX(double x);
	
	void setY(double y);
	
	void setZ(double z);
	
	void setYaw(float yaw);
	
	void setPitch(float pitch);
	
	void setWorld(World world);
	
}
