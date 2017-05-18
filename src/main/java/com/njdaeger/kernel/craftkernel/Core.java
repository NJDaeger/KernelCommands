package com.njdaeger.kernel.craftkernel;

import com.coalesce.plugin.CoPlugin;
import com.njdaeger.kernel.craftkernel.configuration.Config;
import com.njdaeger.kernel.session.User;
import com.njdaeger.kernel.craftkernel.session.UserModule;

public final class Core extends CoPlugin {
	
	private UserModule userModule;
	
	@Override
	public void onPluginEnable() throws Exception {
		new Config(this);
		addModules(
				userModule = new UserModule(this)
		);
	}
	
	@Override
	public void onPluginDisable() throws Exception {
	
	}
	
	public User getUser(String name) {
		return userModule.getUser(name);
	}
	
}
