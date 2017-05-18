package com.njdaeger.kernel.session;

import com.coalesce.config.IConfig;
import com.njdaeger.kernel.Gamemode;

import java.util.UUID;

/**
 * Represents an offline Kernel User.
 */
public interface OfflineUser {
	
	IConfig getConfig();
	
	UUID getId();
	
	boolean isMuted();
	
	void setMuted(boolean mute);
	
	boolean isSpying();
	
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
	
	String getName();
	
}
