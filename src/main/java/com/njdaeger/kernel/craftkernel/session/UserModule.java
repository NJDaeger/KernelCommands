package com.njdaeger.kernel.craftkernel.session;

import com.coalesce.command.CommandContext;
import com.coalesce.plugin.CoModule;
import com.njdaeger.kernel.craftkernel.Core;
import com.njdaeger.kernel.session.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

public final class UserModule extends CoModule {
	
	private final Core plugin;
	private final Collection<User> onlineUsers;
	private final Map<UUID, UserSession> sessionMap;
	
	/**
	 * Create a new module
	 *
	 * @param plugin The plugin that's creating this module
	 */
	public UserModule(Core plugin) {
		super(plugin, "User Module");
		
		this.plugin = plugin;
		this.sessionMap = new HashMap<>();
		this.onlineUsers = new ArrayList<>();
	}
	
	@Override
	protected void onEnable() throws Exception {
		plugin.registerListener(this);
	}
	
	@Override
	protected void onDisable() throws Exception {
	
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		User user = new UserSession(plugin, e.getPlayer());
		user.login();
		sessionMap.put(e.getPlayer().getUniqueId(), (UserSession)user);
		onlineUsers.add(user);
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		User user = getUser(e.getPlayer().getName());
		user.logout();
		sessionMap.remove(user.getId());
		onlineUsers.remove(user.getId());
	}
	
	/**
	 * Gets an online User via name.
	 * @param name The name of the user to get.
	 * @return The user if existent.
	 */
	public User getUser(String name) {
		for (User user : onlineUsers) {
			if (user.getName().equalsIgnoreCase(name)) return user;
		}
		return null;
	}
	
	/**
	 * Gets an online User via UUID.
	 * @param userId The UUID of the user to get.
	 * @return The user if existent.
	 */
	public User getUser(UUID userId) {
		for (User user : onlineUsers) {
			if (user.getId().equals(userId)) return user;
		}
		return null;
	}
	
	/**
	 * Gets an online User via CommandContext.
	 * @param context The context to get the user from.
	 * @return The user if existent.
	 */
	public User getUser(CommandContext context) {
		if (!context.isPlayer()) return null;
		return getUser(context.getSender().getName());
	}
	
	/**
	 * Gets an online User via player.
	 * @param player The player format of the wanted user.
	 * @return The user if existent.
	 */
	public User getUser(Player player) {
		return getUser(player.getUniqueId());
	}
	
	/**
	 * Gets a collection of the online users.
	 * @return A user collection.
	 */
	public Collection<User> getUsers() {
		return onlineUsers;
	}
	
}
