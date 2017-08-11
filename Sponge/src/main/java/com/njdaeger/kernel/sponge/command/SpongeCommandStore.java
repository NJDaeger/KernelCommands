package com.njdaeger.kernel.sponge.command;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.command.base.CommandInfo;
import com.njdaeger.kernel.core.command.base.CommandStore;
import org.spongepowered.api.Sponge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SpongeCommandStore extends CommandStore {
	
	private final IKernel kernel;
	
	public SpongeCommandStore(IKernel kernel) {
		this.kernel = kernel;
	}
	
	@Override
	public void registerCommand(CommandInfo info) {
		List<String> aliases = new ArrayList<>();
		aliases.addAll(Arrays.asList(info.getAliases()));
		aliases.add(info.getName());
		
		Sponge.getCommandManager().register(kernel, new CommandRegister(info, kernel));
	}
	
	@Override
	public boolean isRegistered(String commandName) {
		return Sponge.getCommandManager().get(commandName).isPresent();
	}
	
	
}
