package com.njdaeger.kernel.core;

import static com.coalesce.core.Color.*;

public enum Messages {
    
    CANNOT_RESOLVE_ARG(RED + "Cannot resolve argument: " + SILVER + "{0}"),
    
    HOME_NOT_EXIST(RED + "Home, " + SILVER + "{0}" + RED + ", does not exist."),
    HOME_EXISTS(RED + "Home, " + SILVER + "{0}" + RED + ", already exists."),
    HOME_CREATED(SILVER + "Home, " + GREEN + "{0}" + SILVER + ", created for {1}"),
    HOME_LIST(SILVER + "{0}'s homes: {1}"),
    
    USER_SENT_OTHER_HOME_SENDER(SILVER + "You sent {0} to home: " + GREEN + "{1}"),
    USER_SENT_OTHER_HOME(SILVER + "You were sent to {0}'s home: " + GREEN + "{1}"),
    USER_SENT_HOME(SILVER + "You were sent to home: " + GREEN + "{0}"),
    USER_NOT_EXIST(RED + "User, " + SILVER + "{0}" + RED + ", does not exist.");
    
    private String message;
    
    Messages(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return message;
    }
}
