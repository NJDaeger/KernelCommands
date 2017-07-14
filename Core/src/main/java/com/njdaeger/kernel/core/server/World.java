package com.njdaeger.kernel.core.server;

import java.util.List;
import java.util.UUID;

public interface World {

	String getName();
	
	UUID getID();
	
	List<Player> getPlayers();
	
	
	
}
