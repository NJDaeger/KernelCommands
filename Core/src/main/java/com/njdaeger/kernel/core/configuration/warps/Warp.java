package com.njdaeger.kernel.core.configuration.warps;

import com.coalesce.core.config.YmlConfig;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.server.World;
import com.njdaeger.kernel.core.session.User;

import java.io.File;

public class Warp extends YmlConfig implements IWarp {
    
    private final Location location;
    
    public Warp(String name, Location location) {
        super("worlds" + File.separator + location.getWorld().getName() + "warps" + File.separator + name, Kernel.getKernel());
        this.location = location;
        
        addEntry("x", location.getX());
        addEntry("y", location.getY());
        addEntry("z", location.getZ());
        addEntry("yaw", location.getYaw());
        addEntry("pitch", location.getPitch());
        addEntry("world", location.getWorld().getName());
    }
    
    @Override
    public Location getLocation() {
        if (location != null) {
            return location;
        }
        return new Location(Kernel.getWorld(getWorld().getName()), getX(), getY(), getZ(), getYaw(), getPitch());
    }
    
    @Override
    public void sendHere(User user) {
        user.teleportTo(getLocation());
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
        return getFloat("yaw");
    }
    
    @Override
    public float getPitch() {
        return getFloat("pitch");
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
    public void setYaw(float yaw) {
        setEntry("yaw", yaw);
    }
    
    @Override
    public void setPitch(float pitch) {
        setEntry("pitch", pitch);
    }
    
    @Override
    public void setWorld(World world) {
        setEntry("world", world.getName());
    }
}
