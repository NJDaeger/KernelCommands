package com.njdaeger.kernel.sponge;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.Platform;
import com.njdaeger.kernel.core.command.base.CommandContext;
import com.njdaeger.kernel.core.command.base.CommandInfo;
import com.njdaeger.kernel.core.command.base.KernelCommand;
import com.njdaeger.kernel.core.command.base.KernelCompletion;
import com.njdaeger.kernel.core.command.commands.TestCommand;
import com.njdaeger.kernel.core.server.Player;
import com.njdaeger.kernel.core.server.World;
import com.njdaeger.kernel.sponge.command.CommandRegister;
import org.apache.commons.lang3.Validate;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Plugin(
		id = "kernelcommands",
		name = "KernelCommands Sponge",
		version = "1.0.0",
		authors = "NJDaeger")
public class SpongeKernel implements IKernel {
	
	private final Map<String, Player> players = new HashMap<>();
	private final Map<String, World> worlds = new HashMap<>();
	
	@Listener
	public void onStart(GamePreInitializationEvent e) {
		Kernel.setKernel(this);
		Sponge.getServer().getWorlds().forEach(w -> worlds.put(w.getName(), new SpongeWorld(w)));
		Sponge.getServer().getOnlinePlayers().forEach(p -> players.put(p.getName(), new SpongePlayer(p)));
		
		new TestCommand(this);
	}
	
	public void onStop(GameStoppedServerEvent e) {
		worlds.clear();
		players.clear();
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
		CommandInfo info = null;
		Method method;
		
		try {
			method = Class.forName(command.getClass().getCanonicalName().split("\\$\\$")[0]).getMethod(methodName, CommandContext.class);
			info = new CommandInfo(method, command, null);
		}
		catch (NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (info == null) {
			throw new RuntimeException("Command info could not be loaded for method " + methodName);
		}
		
		List<String> aliases = new ArrayList<>();
		aliases.addAll(Arrays.asList(info.getAliases()));
		aliases.add(info.getName());
		
		Sponge.getCommandManager().register(this, new CommandRegister(info, this), aliases);
	}
	
	@Override
	public void addCommand(String methodName, KernelCommand command, KernelCompletion completion) {
		CommandInfo info = null;
		Method method;
		
		try {
			method = Class.forName(command.getClass().getCanonicalName().split("\\$\\$")[0]).getMethod(methodName, CommandContext.class);
			info = new CommandInfo(method, command, completion);
		}
		catch (NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (info == null) {
			throw new RuntimeException("Command info could not be loaded for method " + methodName);
		}
		
		List<String> aliases = new ArrayList<>();
		aliases.addAll(Arrays.asList(info.getAliases()));
		aliases.add(info.getName());
		
		Sponge.getCommandManager().register(this, new CommandRegister(info, this), aliases);
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
}
