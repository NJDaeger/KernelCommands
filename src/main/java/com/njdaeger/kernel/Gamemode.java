package com.njdaeger.kernel;

public enum Gamemode {
	
	SURVIVAL("0", "survival", "s"),
	CREATIVE("1", "creative", "c"),
	ADVENTURE("2", "adventure", "a"),
	SPECTATOR("3", "spectator");
	
	String[] aliases;
	
	Gamemode(String... aliases) {
		this.aliases = aliases;
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public static Gamemode getAliasUsed(String input) {
		for (Gamemode alias : Gamemode.values()) {
			for (String value : alias.getAliases()) {
				if (value.equals(input)) {
					return alias;
				}
			}
		}
		return null;
	}
	
}
