package com.njdaeger.kernel;

import org.bukkit.WeatherType;

public enum Weather {
	
	CLEAR(WeatherType.CLEAR, "clear", "sunny", "sun"),
	RAIN(WeatherType.DOWNFALL, "rain", "rainy", "gloomy", "lightning", "thunder", "storm");
	
	String[] aliases;
	WeatherType type;
	
	Weather(WeatherType type, String... aliases) {
		this.aliases = aliases;
		this.type = type;
	}
	
	public WeatherType getType() {
		return type;
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public static Weather getFromWeatherType(WeatherType type) {
		for (Weather types : Weather.values()) {
			if (types.getType() == type) {
				return types;
			}
		}
		return null;
	}
	
	public static Weather getAliasUsed(String input) {
		for (Weather unit : Weather.values()) {
			for (String value : unit.getAliases()) {
				if (value.equalsIgnoreCase(input)) {
					return unit;
				}
			}
		}
		return null;
	}
	
}
