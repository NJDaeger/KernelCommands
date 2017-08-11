package com.njdaeger.kernel.sponge;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.Platform;
import com.njdaeger.kernel.core.command.base.CommandStore;
import com.njdaeger.kernel.core.server.Player;
import com.njdaeger.kernel.core.server.World;
import com.njdaeger.kernel.sponge.command.SpongeCommandStore;
import org.apache.commons.lang3.Validate;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;
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
	
	private static final CommandStore STORE;
	private static final Map<String, Player> PLAYERS;
	private static final Map<String, World> WORLDS;
	
	static {
		STORE = new SpongeCommandStore(Kernel.getKernel());
		WORLDS = new HashMap<>();
		PLAYERS = new HashMap<>();
	}
	
	@Listener
	public void onStart(GamePreInitializationEvent e) {
		Kernel.setKernel(this);
		Sponge.getServer().getWorlds().forEach(w -> WORLDS.put(w.getName(), new SpongeWorld(w)));
		Sponge.getServer().getOnlinePlayers().forEach(p -> PLAYERS.put(p.getName(), new SpongePlayer(p)));
	}
	
	public void onStop(GameStoppedServerEvent e) {
		WORLDS.clear();
		PLAYERS.clear();
	}
	
	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join e) {
		PLAYERS.put(e.getTargetEntity().getName(), new SpongePlayer(e.getTargetEntity()));
	}
	
	@Listener
	public void onPlayerLeave(ClientConnectionEvent.Disconnect e) {
		PLAYERS.remove(e.getTargetEntity().getName());
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return PLAYERS.values();
	}
	
	@Override
	public Player getPlayer(String name) {
		Validate.notNull(name, "Player name cannot be null");
		return PLAYERS.get(name);
	}
	
	@Override
	public Player getPlayer(UUID userID) {
		Validate.notNull(userID, "Player UUID cannot be null");
		return PLAYERS.get(Sponge.getServer().getPlayer(userID).orElse(null).getName());
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
	
	@Override
	public Platform getPlatform() {
		return Platform.SPONGE;
	}
	
	@Override
	public File getPluginDirectory() {
		return Sponge.getPluginManager().fromInstance(this).get().getSource().get().getParent().toFile();
	}
	
	@Override
	public CommandStore getCommandStore() {
		return STORE;
	}
}
