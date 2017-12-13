package com.njdaeger.kernel.core.modules.warps;

import com.coalesce.core.SenderType;
import com.coalesce.core.command.annotation.Command;
import com.coalesce.core.command.annotation.Permission;
import com.coalesce.core.command.base.CommandContext;

import static com.njdaeger.kernel.core.Permission.KERNEL_AFK;

public class WarpCommands {
	
	@Permission(KERNEL_AFK)
	@Command(name = "warp", desc = "Warp to a server warp", usage = "/warp <warp> [player]", min = 1, max = 2)
	public void warp(CommandContext context) {
		final SenderType sender = context.getSender().getType();
		
		if (sender == SenderType.BLOCK) {
		
		}
		
		if (sender == SenderType.CONSOLE) {
		
		}
		
		if (sender == SenderType.PLAYER) {
		
		}
		
		else {
		
		}
		
	}
	
}
