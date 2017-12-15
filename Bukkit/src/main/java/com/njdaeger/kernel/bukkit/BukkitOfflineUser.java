package com.njdaeger.kernel.bukkit;

import com.coalesce.core.session.AbstractSession;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.configuration.UserFile;
import com.njdaeger.kernel.core.configuration.homes.IOfflineHome;
import com.njdaeger.kernel.core.configuration.homes.OfflineHome;
import com.njdaeger.kernel.core.server.Location;
import com.njdaeger.kernel.core.session.OfflineUser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static com.njdaeger.kernel.core.UserPaths.*;

public class BukkitOfflineUser extends AbstractSession<UUID> implements OfflineUser {
    
    private final String name;
    private final UUID userID;
    private final UserFile userFile;
    private final Map<String, IOfflineHome> homes;
    
    public BukkitOfflineUser(String sessionKey, UUID userID) {
        super(Kernel.getKernel(), "offlineUsers", sessionKey, userID);
        
        this.name = sessionKey;
        this.userID = userID;
        this.homes = new HashMap<>();
        this.userFile = new UserFile(this);
    
        File homesDir = new File(userFile.getDirectory() + File.separator + "homes");
        if (homesDir.exists()) {
            if (homesDir.listFiles() != null) {
                Stream.of(homesDir.listFiles()).forEach(f -> {
                    String name = f.getName().substring(0, f.getName().lastIndexOf("."));
                    homes.put(name, new OfflineHome(this, name));
                });
            }
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public IOfflineHome getHome(String name) {
        return homes.get(name);
    }
    
    @Override
    public void addHome(Location location, String name) {
        new OfflineHome(this, location, name);
    }
    
    @Override
    public void removeHome(String name) {
        homes.remove(name).delete();
    }
    
    @Override
    public void removeHome(IOfflineHome home) {
        homes.remove(home.getName());
        home.delete();
    }
    
    @Override
    public List<IOfflineHome> getHomes() {
        return new ArrayList<>(homes.values());
    }
    
    @Override
    public UUID getUserID() {
        return userID;
    }
    
    @Override
    public UserFile getUserFile() {
        return userFile;
    }
    
    @Override
    public Location getLocation() {
        return new Location(
                Kernel.getWorld(userFile.getString(CURRENT_WORLD.getPath())),
                userFile.getDouble(CURRENT_X.getPath()),
                userFile.getDouble(CURRENT_Y.getPath()),
                userFile.getDouble(CURRENT_Z.getPath()),
                Float.valueOf(String.valueOf(userFile.getDouble(CURRENT_YAW.getPath()))),
                Float.valueOf(String.valueOf(userFile.getDouble(CURRENT_PITCH.getPath())))
        );
    }
}
