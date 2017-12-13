package com.njdaeger.kernel.bukkit;

import com.coalesce.core.bukkit.CoPlugin;
import com.coalesce.core.session.NamespacedSessionStore;
import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.Register;
import com.njdaeger.kernel.core.configuration.UserDatabase;
import com.njdaeger.kernel.core.server.World;
import com.njdaeger.kernel.core.session.OfflineUser;
import com.njdaeger.kernel.core.session.User;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BukkitKernel extends CoPlugin implements IKernel, Listener {
	
	private NamespacedSessionStore<BukkitUser> userNamespace;
	private static final Map<String, World> WORLDS;
	private UserDatabase userdata;
	
	static {
		WORLDS = new HashMap<>();
		
	}
	
	@Override
	public void onPluginEnable() throws Exception {
		//Set the kernel
		Kernel.setKernel(this);
		
		//Registers the listeners in this class
		registerListener(this);
		
		//Userdata.
		userdata = new UserDatabase();
		
		//Creates instances of wrapped worlds
		Bukkit.getWorlds().forEach(w -> WORLDS.put(w.getName(), new BukkitWorld(w)));
		
		//Creates the userNamespace and adds users to the namespace.
		userNamespace = getSessionStore().addNamespace("users", BukkitUser.class);
		Bukkit.getOnlinePlayers().forEach(p -> {
			User user = userNamespace.addSession(new BukkitUser(p.getName(), p));
			user.login();
			getUserDatabase().addUser(user);
		});
		
		//Runs the common portion of the onEnable.
		Register.enable();
	}
	
	@Override
	public void onPluginDisable() throws Exception {
		Register.disable();
		
		userNamespace.getSessions().forEach(s -> {
			s.logout();
			userNamespace.removeSession(s.getName());
		});
		
		WORLDS.clear();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		User user = userNamespace.addSession(new BukkitUser(e.getPlayer().getName(), e.getPlayer()));
		user.login();
		getUserDatabase().addUser(user);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		User user = userNamespace.getSession(e.getPlayer().getName());
		user.logout();
		userNamespace.removeSession(user.getName());
	}
	
	@Override
	public Collection<User> getUsers() {
		return new ArrayList<>(userNamespace.getSessions());
	}
	
	@Override
	public User getUser(String name) {
		Validate.notNull(name, "Player name cannot be null");
		return userNamespace.getSession(name);
	}
	
	@Override
	public OfflineUser getOfflineUser(String name) {
		Validate.notNull(name, "Player name cannot be null");
		if (!userdata.contains(name, true) || userdata.getEntry(name).getValue() == null) return null;
		else return new BukkitOfflineUser(name, UUID.fromString(userdata.getValue(name).toString()));
	}
	
	@Override
	public Collection<World> getWorlds() {
		return WORLDS.values();
	}
	
	@Override
	public World getWorld(String name) {
		Validate.notNull(name, "World name cannot be null");
		return WORLDS.get(name);
	}
	
	@Override
	public UserDatabase getUserDatabase() {
		return userdata;
	}
}
