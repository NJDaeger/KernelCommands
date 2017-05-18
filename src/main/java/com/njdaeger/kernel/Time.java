package com.njdaeger.kernel;

public enum Time {
	MORNING(0, "morn", "morning", "sunrise", "dawn"),
	DAY(6000, "day", "noon", "midday", "middle"),
	EVENING(12000, "even", "evening", "dusk", "sunset"),
	NIGHT(18000, "night", "midnight", "dark");
	
	String[] aliases;
	long time;
	
	Time(long time, String... aliases) {
		this.aliases = aliases;
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public static Time getClosestTime(long input) {
		for (Time unit : Time.values()) {
			long time = unit.getTime() - input;
			long abstime = Math.abs(time);
			if (abstime <= 6000) {
				return unit;
			}
		}
		return null;
	}
	
	public static Time getAliasUsed(String input) {
		for (Time unit : Time.values()) {
			for (String value : unit.getAliases()) {
				if (value.equalsIgnoreCase(input)) {
					return unit;
				}
			}
		}
		return null;
	}
}
