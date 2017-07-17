package com.njdaeger.kernel.sponge;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.command.CommandInfo;
import com.njdaeger.kernel.core.command.base.KernelCommand;
import com.njdaeger.kernel.core.command.base.KernelCompletion;
import com.njdaeger.kernel.core.server.Player;
import org.apache.commons.lang3.Validate;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Plugin(
		id = "kernelcommands",
		name = "KernelCommands Sponge",
		version = "1.0.0",
		authors = "NJDaeger")
public class SpongeKernel implements IKernel {
	
	private final Map<String, Player> players = new HashMap<>();
	
	@Listener
	public void onStart(GameStartingServerEvent e) {
		Kernel.setKernel(this);
		Sponge.getServer().getWorlds().forEach(SpongeWorld::new);
		Sponge.getServer().getOnlinePlayers().forEach(SpongePlayer::new);
	}
	
	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join e) {
		players.put(e.getTargetEntity().getName(), new SpongePlayer(e.getTargetEntity()));
	}
	
	@Listener
	public void onPlayerLeave(ClientConnectionEvent.Disconnect e) {
		players.remove(e.getTargetEntity().getName());
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return players.values();
	}
	
	@Override
	public Player getPlayer(String name) {
		Validate.notNull(name, "Player name cannot be null");
		return players.get(name);
	}
	
	@Override
	public Player getPlayer(UUID userID) {
		Validate.notNull(userID, "Player UUID cannot be null");
		return players.get(Sponge.getServer().getPlayer(userID).orElse(null).getName());
	}
	
	@Override
	public void addCommand(String methodName, KernelCommand command) {
		//new CommandInfo(method, command, null);
	}
	
	@Override
	public void addCommand(String methodName, KernelCommand command, KernelCompletion completion) {
		//new CommandInfo(method, command, completion);
	}
	
	@Override
	public String getName() {
		if (Sponge.getPluginManager().fromInstance(this).isPresent()) {
			return Sponge.getPluginManager().fromInstance(this).get().getName();
		}
		else return "Unknown";
	}
	
	@Override
	public String getVersion() {
		if (Sponge.getPluginManager().fromInstance(this).isPresent()) {
			return Sponge.getPluginManager().fromInstance(this).get().getVersion().orElse("Unknown");
		}
		else return "Unknown";
	}
	
	@Override
	public String getAuthors() {
		if (Sponge.getPluginManager().fromInstance(this).isPresent()) {
			return Arrays.toString(Sponge.getPluginManager().fromInstance(this).get().getAuthors().toArray());
		}
		return "Unknown";
	}
}
