package com.njdaeger.kernel.session;

import com.coalesce.config.yml.YamlConfig;
import com.njdaeger.kernel.Core;
import org.bukkit.entity.Player;

public final class UserSession extends YamlConfig {
	
	//The player represented by this user.
	private final Player player;
	
	public UserSession(Core core, Player player) {
		super(player.getName(), core);
		this.player = player;
	}
	
}
