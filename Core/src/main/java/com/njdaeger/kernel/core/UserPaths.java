package com.njdaeger.kernel.core;

public enum UserPaths {
    
    PLAYERNAME("playername", null),
    NICKNAME("nickname", null),
    ID("uuid", null),
    IP("ip", null),
    CURRENT_X("current.x", null),
    CURRENT_Y("current.y", null),
    CURRENT_Z("current.z", null),
    CURRENT_YAW("current.yaw", null),
    CURRENT_PITCH("current.pitch", null),
    CURRENT_WORLD("current.world", null);
    
    private String path;
    private Object defVal;
    
    UserPaths(String path, Object defVal) {
        this.defVal = defVal;
        this.path = path;
    }
    
    /**
     * Get the path of this setting
     *
     * @return The path
     */
    public String getPath() {
        return path;
    }
    
    /**
     * Gets the default setting of this path
     *
     * @return The default value
     */
    public Object getDefVal() {
        return defVal;
    }
}
