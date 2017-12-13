package com.njdaeger.kernel.core.server;

import com.njdaeger.kernel.core.configuration.Locatable;
import com.njdaeger.kernel.core.configuration.Relocatable;

public final class Location implements Locatable, Relocatable{
	
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;
	private World world;
	
	public Location(World world, double x, double y, double z) {
		this(world, x, y, z, 0, 0);
	}
	
	public Location(World world, double x, double y, double z, float yaw, float pitch) {
		this.world = world;
		this.pitch = pitch;
		this.yaw = yaw;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	@Override
	public double getZ() {
		return z;
	}
	
	@Override
	public float getYaw() {
		return yaw;
	}
	
	@Override
	public float getPitch() {
		return pitch;
	}
	
	@Override
	public World getWorld() {
		return world;
	}
	
	@Override
	public void setX(double x) {
		this.x = x;
	}
	
	@Override
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public void setZ(double z) {
		this.z = z;
	}
	
	@Override
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	@Override
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	@Override
	public void setWorld(World world) {
		this.world = world;
	}
}
