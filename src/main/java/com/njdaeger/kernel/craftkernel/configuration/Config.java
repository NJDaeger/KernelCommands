package com.njdaeger.kernel.craftkernel.configuration;

import com.coalesce.config.yml.YamlConfig;
import com.njdaeger.kernel.craftkernel.Core;

public final class Config extends YamlConfig {
	
	public Config(Core core) {
		super("config", core);
	}
	
}
