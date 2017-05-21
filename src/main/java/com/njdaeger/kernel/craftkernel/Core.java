package com.njdaeger.kernel.craftkernel;

import com.coalesce.plugin.CoPlugin;
import com.njdaeger.kernel.commands.CommandModule;
import com.njdaeger.kernel.craftkernel.configuration.Config;
import com.njdaeger.kernel.craftkernel.session.UserModule;

public final class Core extends CoPlugin {
	
	private UserModule userModule;
	
	@Override
	public void onPluginEnable() throws Exception {
		new Config(this);
		addModules(
				userModule = new UserModule(this),
				new CommandModule(this)
		);
	}
	
	@Override
	public void onPluginDisable() throws Exception {
	
	}
	
	/**
	 * Gets the user module.
	 * @return The user module.
	 */
	public UserModule getUserModule() {
		return userModule;
	}
}
