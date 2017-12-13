package com.njdaeger.kernel.core.configuration.homes;

import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.session.User;

public final class Home extends OfflineHome implements IHome {
	
	private final User user;
	
	public Home(User user, String name) {
		super(user, name);
		this.user = user;
	}
	
	public Home(User user, Location location, String name) {
		super(user, location, name);
		this.user = user;
	}
	
	@Override
	public User getOwner() {
		return user;
	}
	
	@Override
	public void sendHere() {
		user.teleportHome(this);
	}
	
	@Override
	public void sendOtherHere(User user) {
		user.teleportHome(this);
	}
	
}
