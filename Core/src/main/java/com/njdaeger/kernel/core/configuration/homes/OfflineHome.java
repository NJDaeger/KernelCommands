package com.njdaeger.kernel.core.configuration.homes;

import com.coalesce.core.config.YmlConfig;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.server.World;
import com.njdaeger.kernel.core.session.OfflineUser;

import java.io.File;

public class OfflineHome extends YmlConfig implements IOfflineHome {
    
    private final OfflineUser user;
    private final String name;
    private Location location;
    
    public OfflineHome(OfflineUser user, String name) {
        super("users" + File.separator + user.getUserID().toString() + File.separator + "homes" + File.separator + name, Kernel.getKernel());
        this.user = user;
        this.name = name;
        
        Location loc = location == null ? user.getLocation() : location;
        setX(loc.getX());
        setY(loc.getY());
        setZ(loc.getZ());
        setYaw(loc.getYaw());
        setPitch(loc.getPitch());
        setWorld(loc.getWorld());
        
    }
    
    public OfflineHome(OfflineUser user, Location location, String name) {
        this(user, name);
        this.location = location;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public boolean delete() {
        return super.delete();
    }
    
    @Override
    public Location getLocation() {
        if (location != null) {
            return location;
        }
        return new Location(Kernel.getWorld(getWorld().getName()), getX(), getY(), getZ(), getYaw(), getPitch());
    }
    
    @Override
    public OfflineUser getOwner() {
        return user;
    }
    
    @Override
    public double getX() {
        return getDouble("x");
    }
    
    @Override
    public double getY() {
        return getDouble("y");
    }
    
    @Override
    public double getZ() {
        return getDouble("z");
    }
    
    @Override
    public float getYaw() {
        return getEntry("yaw").getAs(Float.class);
    }
    
    @Override
    public float getPitch() {
        return getEntry("pitch").getAs(Float.class);
    }
    
    @Override
    public World getWorld() {
        return Kernel.getWorld(getString("world"));
    }
    
    @Override
    public void setX(double x) {
        setEntry("x", x);
    }
    
    @Override
    public void setY(double y) {
        setEntry("y", y);
    }
    
    @Override
    public void setZ(double z) {
        setEntry("z", z);
    }
    
    @Override
    public void setWorld(World world) {
        setEntry("world", world.getName());
    }
    
    @Override
    public void setYaw(float yaw) {
        setEntry("yaw", yaw);
    }
    
    @Override
    public void setPitch(float pitch) {
        setEntry("pitch", pitch);
    }
}
