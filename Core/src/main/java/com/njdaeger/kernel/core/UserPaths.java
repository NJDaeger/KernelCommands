package com.njdaeger.kernel.core;

public enum UserPaths {
	
	PLAYERNAME("playername", null),
	NICKNAME("nickname", null),
	ID("uuid", null),
	IP("ip", null),
	CURRENT_WORLD("world", null);
	
	private String path;
	private Object defVal;
	
	UserPaths(String path, Object defVal) {
		this.defVal = defVal;
		this.path = path;
	}
	
	/**
	 * Get the path of this setting
	 * @return The path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Gets the default setting of this path
	 * @return The default value
	 */
	public Object getDefVal() {
		return defVal;
	}
}
