package com.njdaeger.kernel.core.command.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class CommandStore {
	
	private final Map<String[], CompletionInfo> completionMap;
	private final Map<String, CommandInfo> commandMap;
	
	public CommandStore() {
		this.commandMap = new HashMap<>();
		this.completionMap = new HashMap<>();
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
	}
	
	/**
	 * Unregisters a class' commands and tab completions. (anything annotated with Command and Completion)
	 * @param commandClass The class to unregister commands.
	 */
	public void unregisterClass(Class<?> commandClass) {
		List<String> removedCommands = new ArrayList<>();
		List<String[]> removedCompletions = new ArrayList<>();
		commandMap.forEach((n, i) -> {
			if (i.getMethod().getDeclaringClass().equals(commandClass)) removedCommands.add(n);
		});
		completionMap.forEach((n, i) -> {
			if (i.getMethod().getDeclaringClass().equals(commandClass)) removedCompletions.add(i.getCommands());
		});
		removedCommands.forEach(commandMap::remove);
		removedCompletions.forEach(completionMap::remove);
	}
	
	/**
	 * Unregisters a command by name (Will not remove the command from any tab completions)
	 * @param name The name of the command
	 */
	public void unregisterCommand(String name) {
		commandMap.remove(name);
	}
	
	/**
	 * Checks if a command is registered or not
	 * @param command The command to check if registered
	 * @return True if the command is registered in the Kernel's command map
	 */
	public boolean isRegistered(String command) {
		return commandMap.keySet().contains(command);
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
