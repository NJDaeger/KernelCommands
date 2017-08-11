package com.njdaeger.kernel.core.command.base;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class CommandStore {
	
	private final Map<String[], CompletionInfo> completionMap;
	private final Map<String, CommandInfo> commandMap;
	
	public CommandStore() {
		this.commandMap = new HashMap<>();
		this.completionMap = new HashMap<>();
	}
	
	/**
	 * Registers a command into either platforms command map
	 * @param info The command information
	 */
	public abstract void registerCommand(CommandInfo info);

	public abstract boolean isRegistered(String commandName);
	
	public CommandInfo getCommand(String name) {
		return getCommandMap().get(name);
	}
	
	/**
	 * Registers a class' commands and tab completions. (anything annotated with Command and Completion)
	 * @param container The Object the command is in
	 */
	public void registerClass(Object container) {
		Stream.of(container.getClass().getDeclaredMethods()).forEach(m -> {
			if (m.isAnnotationPresent(Command.class)) {
				CommandInfo info = new CommandInfo(container, m);
				commandMap.put(info.getName(), info);
			}
			if (m.isAnnotationPresent(Completion.class)) {
				CompletionInfo info = new CompletionInfo(container, m);
				completionMap.put(info.getCommands(), info);
			}
		});
		updateMaps();
	}
	
	private void updateMaps() {
		completionMap.forEach((commands, cinfo) -> {
			for (String command : commands) {
				if (commandMap.keySet().contains(command)) {
					CommandInfo info = commandMap.get(command);
					info.setCompletionInfo(cinfo);
					commandMap.put(command, info);
				}
			}
		});
		commandMap.forEach((name, info) -> {
			if (!isRegistered(name)) registerCommand(info);
		});
	}
	
	/**
	 * Get the Kernel's command map
	 * @return The Kernels Command map
	 */
	public Map<String, CommandInfo> getCommandMap() {
		return commandMap;
	}
	
	/**
	 * Get the Kernel's completion map
	 * @return The Kernels completion map
	 */
	public Map<String[], CompletionInfo> getCompletionMap() {
		return completionMap;
	}
	
}
