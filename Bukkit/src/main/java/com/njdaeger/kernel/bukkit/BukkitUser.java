package com.njdaeger.kernel.bukkit;

import com.coalesce.core.session.AbstractSession;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.UserPaths;
import com.njdaeger.kernel.core.configuration.UserFile;
import com.njdaeger.kernel.core.configuration.homes.Home;
import com.njdaeger.kernel.core.configuration.homes.IHome;
import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.session.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class BukkitUser extends AbstractSession<Player> implements User {
    
    private final Map<UserPaths, Object> userPaths;
    private final Map<String, IHome> homes;
    private final UserFile userFile;
    private final Player player;
    
    public BukkitUser(String sessionKey, Player type) {
        super(Kernel.getKernel(), "users", sessionKey, type);
        
        this.player = type;
        this.homes = new HashMap<>();
        this.userPaths = new HashMap<>();
        this.userFile = new UserFile(this);
    }
    
    @Override
    public void login() {
        //Set all the values in userPaths
        Stream.of(UserPaths.values()).forEach(p -> userPaths.put(p, userFile.getValue(p)));
        
        userPaths.putIfAbsent(UserPaths.NICKNAME, player.getDisplayName());
        userPaths.putIfAbsent(UserPaths.PLAYERNAME, player.getName());
        userPaths.putIfAbsent(UserPaths.CURRENT_WORLD, player.getWorld().getName());
        userPaths.putIfAbsent(UserPaths.ID, player.getUniqueId().toString());
        userPaths.putIfAbsent(UserPaths.IP, player.getAddress().getAddress().toString());
        
        
        File homesDir = new File(userFile.getDirectory() + File.separator + "homes");
        if (homesDir.exists()) {
            if (homesDir.listFiles() != null) {
                Stream.of(homesDir.listFiles()).forEach(f -> {
                    String name = f.getName().substring(0, f.getName().lastIndexOf("."));
                    homes.put(name, new Home(this, name));
                });
            }
        }
    }
    
    @Override
    public void logout() {
        userPaths.forEach((p, v) -> userFile.setEntry(p.getPath(), v));
        homes.clear();
    }
    
    @Override
    public UUID getUserID() {
        return player.getUniqueId();
    }
    
    @Override
    public UserFile getUserFile() {
        return userFile;
    }
    
    @Override
    public String getName() {
        return player.getName();
    }
    
    @Override
    public IHome getHome(String name) {
        if (name == null) {
            return null;
        }
        return homes.get(name);
    }
    
    @Override
    public void addHome(Location location, String name) {
        IHome home = new Home(this, location, name);
        homes.put(name, home);
    }
    
    @Override
    public void addHome(String name) {
        IHome home = new Home(this, name);
        homes.put(name, home);
    }
    
    @Override
    public List<IHome> getHomes() {
        return new ArrayList<>(homes.values());
    }
    
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }
    
    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }
    
    @Override
    public void sendMessage(String... message) {
        player.sendMessage(message);
    }
    
    
    @Override
    public void teleportTo(Location location) {
        player.teleport(new org.bukkit.Location(Bukkit.getWorld(location.getWorld().getName()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
    }
    
    @Override
    public Location getLocation() {
        return new Location(Kernel.getWorld(player.getWorld().getName()), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
    }
}
