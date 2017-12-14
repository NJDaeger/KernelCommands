package com.njdaeger.kernel.bukkit;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.server.Sender;
import com.njdaeger.kernel.core.server.SenderType;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BukkitSender implements Sender {
    
    private final CommandSender sender;
    private final IKernel kernel;
    
    public BukkitSender(CommandSender sender, IKernel kernel) {
        this.sender = sender;
        this.kernel = kernel;
    }
    
    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }
    
    @Override
    public IKernel getKernel() {
        return kernel;
    }
    
    @Override
    public String getName() {
        return sender.getName();
    }
    
    @Override
    public SenderType getType() {
        if (sender instanceof Player) {
            return SenderType.PLAYER;
        }
        if (sender instanceof BlockCommandSender) {
            return SenderType.BLOCK;
        }
        if (sender instanceof ConsoleCommandSender) {
            return SenderType.CONSOLE;
        }
        return SenderType.OTHER;
    }
    
    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
