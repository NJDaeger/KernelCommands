package com.njdaeger.kernel.core.configuration.common;

import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.configuration.Format;
import com.njdaeger.kernel.core.configuration.IConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public abstract class Config implements IConfig {
	
	private final Format format;
	private final String name;
	private final File dir, file;
	
	public Config(Format format, String name) {
		this.dir = Kernel.getPluginDirectory();
		this.format = format;
		this.name = name;
		if (format == Format.YML) {
			this.file = new File(dir, name + ".yml");
			
			if (!file.exists()) {
				try {
					file.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			this.file = new File(dir, name + ".json");
			
			if (!file.exists()) {
				try {
					file.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Config(Format format, String name, File location) {
		this.dir = Kernel.getPluginDirectory();
		this.format = format;
		this.name = name;
		if (format == Format.YML) {
			this.file = new File(name + ".yml");
			
			if (!file.exists()) {
				try {
					location.mkdirs();
					Yaml yaml = new Yaml();
					file.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			this.file = new File( name + ".json");
			
			if (!file.exists()) {
				try {
					location.mkdirs();
					file.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
