package com.njdaeger.kernel.sponge;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.server.Sender;
import com.njdaeger.kernel.core.server.SenderType;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class SpongeSender implements Sender {
	
	private final CommandSource sender;
	private final IKernel kernel;
	
	public SpongeSender(CommandSource sender, IKernel kernel) {
		this.sender = sender;
		this.kernel = kernel;
	}
	
	@Override
	public void sendMessage(String message) {
		sender.sendMessage(Text.of(message));
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
		if (sender instanceof ConsoleSource) return SenderType.CONSOLE;
		if (sender instanceof CommandBlockSource) return SenderType.BLOCK;
		if (sender instanceof Player) return SenderType.PLAYER;
		return SenderType.OTHER;
	}
	
	@Override
	public boolean hasPermission(String permission) {
		return sender.hasPermission(permission);
	}
	
	@Override
	public boolean isOp() {
		//Probs add my own impl. of isop
		return true;
	}
}
