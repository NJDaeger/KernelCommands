package com.njdaeger.kernel.commands;

import com.coalesce.command.CoCommand;
import com.coalesce.command.CommandBuilder;
import com.coalesce.command.CommandContext;
import com.coalesce.plugin.CoModule;
import com.njdaeger.kernel.craftkernel.Core;
import com.njdaeger.kernel.session.User;
import org.bukkit.entity.Player;

public final class CommandModule extends CoModule {
	
	private final Core core;
	
	public CommandModule(Core core) {
		super(core, "Player Commands");
		this.core = core;
	}
	
	@Override
	protected void onEnable() throws Exception {
		CoCommand command = new CommandBuilder(core, "world")
				.executor(this::world)
				.description("Your current world")
				.maxArgs(0)
				.permission("kernel.world")
				.build();
		core.addCommand(command);
	}
	
	@Override
	protected void onDisable() throws Exception {
	
	}
	
	private void world(CommandContext context) {
		User user = core.getUserModule().getUser(context);
		user.sendMessage(String.valueOf(user.getLoginLocation().getX()));
	}
	
}
