package com.njdaeger.kernel.session;

import com.njdaeger.kernel.Time;
import com.njdaeger.kernel.Weather;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Represents an online Kernel user.
 */
public interface User extends OfflineUser {
	
	void login();
	
	void logout();
	
	boolean hasPermission(Permission... permission);
	
	boolean hasPermission(String... permission);
	
	void teleport(Location location);
	
	void sudo(String command);
	
	void setUserTime(Time time);
	
	void setUserTime(long time);
	
	Time getUserTime();
	
	void resetUserTime();
	
	void setUserWeather(Weather weather);
	
	Weather getUserWeather();
	
	void resetUserWeather();
	
	Player getPlayer();
	
	
}