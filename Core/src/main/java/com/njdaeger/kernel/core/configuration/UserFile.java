package com.njdaeger.kernel.core.configuration;

import com.coalesce.core.config.YmlConfig;
import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.UserPaths;
import com.njdaeger.kernel.core.session.OfflineUser;

import java.io.File;

public final class UserFile extends YmlConfig {
    
    public UserFile(OfflineUser user) {
        super("users" + File.separator + user.getUserID().toString() + File.separator + "user", Kernel.getKernel());
    }
    
    /**
     * Gets the value of a configuration option
     *
     * @param path The path
     * @return The current value of the variable, or the default value of it if its not in the file yet.
     */
    public Object getValue(UserPaths path) {
        if (contains(path.getPath(), true)) {
            return path.getDefVal();
        }
        return getValue(path.getPath());
    }
}
