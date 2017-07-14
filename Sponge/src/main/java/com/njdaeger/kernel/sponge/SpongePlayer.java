package com.njdaeger.kernel.sponge;

import com.njdaeger.kernel.core.Permission;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.UUID;

public class SpongePlayer implements com.njdaeger.kernel.core.server.Player {
	
	private final Player player;
	
	public SpongePlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public UUID getUserID() {
		return player.getUniqueId();
	}
	
	@Override
	public boolean hasPermission(String permission) {
		return player.hasPermission(permission);
	}
	
	@Override
	public boolean hasPermission(Permission permission) {
		return player.hasPermission(permission.getNode());
	}
	
	@Override
	public void sendMessage(String message) {
		player.sendMessage(Text.of(message));
	}
	
	@Override
	public void sendMessage(String... message) {
		for (String  line : message) {
			player.sendMessage(Text.of(line));
		}
	}
	
	@Override
	public void pluginMessage(String message) {
	
	}
}
