package com.njdaeger.kernel.core.configuration.common;

import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.configuration.Format;
import com.njdaeger.kernel.core.configuration.IConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class Config implements IConfig {
	
	private final Format format;
	private final String name;
	private final File dir, file;
	
	public Config(Format format, String name) {
		
		this.dir = Kernel.getPluginDirectory();
		this.format = format;
		this.name = name;
		FileInputStream stream = null;
		
		if (format == Format.YML) {
			this.file = new File(dir + File.separator + "KernelCommands", name + ".yml");
			if (!file.exists()) {
				
				try {
					file.createNewFile();
					stream = new FileInputStream(file);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			Yaml yaml = new Yaml();
			yaml.dump(yaml.load(stream));
		}
		
		else{
			this.file = new File(dir, name + ".json");
		}
	}
	
	public Config(Format format, String name, File location) {
		
		this.dir = Kernel.getPluginDirectory();
		this.format = format;
		this.name = name;
		FileInputStream stream = null;
		
		if (format == Format.YML) {
			this.file = new File(dir + File.separator + location, name + ".yml");
			if (!file.exists()) {
				
				try {
					file.createNewFile();
					stream = new FileInputStream(file);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			Yaml yaml = new Yaml();
			yaml.dump(yaml.load(stream));
		}
		
		else{
			this.file = new File( name + ".json");
		}
		
	}
}
