package com.njdaeger.kernel.sponge.command;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.command.base.CommandInfo;
import com.njdaeger.kernel.sponge.SpongeSender;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public final class CommandRegister implements CommandCallable {
	
	private final CommandInfo command;
	private final IKernel kernel;
	
	public CommandRegister(CommandInfo info, IKernel kernel) {
		this.command = info;
		this.kernel = kernel;
	}
	
	@Override
	public CommandResult process(CommandSource source, String arguments) throws CommandException {
		command.run(new SpongeSender(source, kernel), (arguments.isEmpty() ? new String[0] : arguments.split(" ")));
		return CommandResult.success();
	}
	
	@Override
	public List<String> getSuggestions(CommandSource source, String arguments, @Nullable Location<World> targetPosition) throws CommandException {
		return command.complete(new SpongeSender(source, kernel), arguments.split(" "));
	}
	
	@Override
	public boolean testPermission(CommandSource sender) {
		boolean permission = false;
		if (command.getPermissions().length != 0) {
			for (String node : command.getPermissions()) {
				if (sender.hasPermission(node)) {
					permission = true;
					break;
				}
			}
		} else permission = true;
		return permission;
	}
	
	@Override
	public Optional<Text> getShortDescription(CommandSource source) {
		return Optional.of(Text.of(command.getDesc()));
	}
	
	@Override
	public Optional<Text> getHelp(CommandSource source) {
		return Optional.of(Text.of(command.getDesc()));
	}
	
	@Override
	public Text getUsage(CommandSource source) {
		return Text.of(command.getUsage());
	}
}
