package com.njdaeger.kernel.session;

import com.coalesce.config.IConfig;
import com.njdaeger.kernel.Gamemode;
import org.bukkit.Location;

import java.util.UUID;

/**
 * Represents an offline Kernel User.
 */
public interface OfflineUser {
	
	/**
	 * Returns the player's configuration.
	 * @return The player configuration.
	 */
	IConfig getConfig();
	
	/**
	 * Gets the UUID of the user.
	 * @return The user UUID.
	 */
	UUID getId();
	
	/**
	 * Checks if the user is muted.
	 * @return True if the user is muted, false otherwise.
	 */
	boolean isMuted();
	
	/**
	 * Mutes the user
	 * @param mute True mutes the user, false does otherwise.
	 */
	void setMuted(boolean mute);
	
	/**
	 * Checks if the user is spying
	 * @return True if the user is spying, false otherwise.
	 */
	boolean isSpying();
	
	/**
	 * Sets the user into spying mode.
	 * @param spying True to enable spying, false otherwise.
	 */
	void setSpying(boolean spying);
	
	boolean isGod();
	
	void setGod(boolean god);
	
	boolean isMessageable();
	
	void setMessageable(boolean messageable);
	
	boolean isAfk();
	
	void setAfk(boolean afk);
	
	boolean isTeleportable();
	
	void setTeleportable(boolean teleportable);
	
	String getNickname();
	
	boolean isNicknamed();
	
	void setNickname(String nickname);
	
	double getFlyingSpeed();
	
	void setFlyingSpeed(double speed);
	
	double getWalkingSpeed();
	
	void setWalkingSpeed(double speed);
	
	boolean isOp();
	
	void setOp(boolean op);
	
	Gamemode getGamemode();
	
	void setGamemode(Gamemode gamemode);
	
	void setGamemode(String gamemode);
	
	boolean isHidden();
	
	void setHidden(boolean hidden);
	
	String getIp();
	
	/**
	 * Gets the name of the user.
	 * @return The
	 */
	String getPlayerName();
	
	LoginLocation getLoginLocation();
	
	LastLocation getLastLocation();
	
	Location getLocation();
	
}
