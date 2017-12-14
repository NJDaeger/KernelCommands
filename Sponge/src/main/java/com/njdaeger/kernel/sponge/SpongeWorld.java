package com.njdaeger.kernel.sponge;

import org.spongepowered.api.world.World;

import java.util.List;
import java.util.UUID;

public class SpongeWorld implements com.njdaeger.kernel.core.server.World {

    private final World world;

    public SpongeWorld(World world) {
        this.world = world;
    }

    @Override
    public String getName() {
        return world.getName();
    }

    @Override
    public UUID getID() {
        return world.getUniqueId();
    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }
}
