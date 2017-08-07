package com.njdaeger.kernel.core.configuration.yml;

import com.njdaeger.kernel.core.Kernel;
import com.njdaeger.kernel.core.configuration.Format;
import com.njdaeger.kernel.core.configuration.IConfig;
import com.njdaeger.kernel.core.configuration.IEntry;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class YmlConfig implements IConfig {
	
	private FileInputStream stream = null;
	private static final Map<?, ?> KEYS;
	private static final Yaml YAML;
	private static final File DIR;
	private final String name;
	private final File file;
	
	public YmlConfig(String name) {
		
		this.file = new File(DIR + File.separator + "KernelCommands", name + ".yml");
		this.name = name;
		
		if (!file.exists()) {
			try {
				this.file.createNewFile();
				this.stream = new FileInputStream(file);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		YAML.dump(YAML.load(stream));
		
	}
	
	public YmlConfig(String name, File location) {
		
		this.file = new File(DIR + File.separator + location, name + ".yml");
		this.name = name;
		
		if (!file.exists()) {
			try {
				this.file.createNewFile();
				this.stream = new FileInputStream(file);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		YAML.dump(YAML.load(stream));
		
	}
	
	static {
		
		KEYS = new HashMap<>();
		YAML = new Yaml();
		DIR = Kernel.getPluginDirectory();
	}
	
	@Override
	public IEntry getEntry(String path) {
		return null;
	}
	
	@Override
	public String getString(String path) {
		return null;
	}
	
	@Override
	public double getDouble(String path) {
		return 0;
	}
	
	@Override
	public int getInt(String path) {
		return 0;
	}
	
	@Override
	public long getLong(String path) {
		return 0;
	}
	
	@Override
	public boolean getBoolean(String path) {
		return false;
	}
	
	@Override
	public List<?> getList(String path) {
		return null;
	}
	
	@Override
	public List<String> getStringList(String path) {
		return null;
	}
	
	@Override
	public Object getValue(String path) {
		return null;
	}
	
	@Override
	public Collection<IEntry> getEntryFromValue() {
		return null;
	}
	
	@Override
	public Collection<IEntry> getEntries() {
		return null;
	}
	
	@Override
	public void addEntry(String path, Object value) {
	
	}
	
	@Override
	public void setEntry(String path, Object value) {
	
	}
	
	@Override
	public void deleteEntry(String path) {
	
	}
	
	@Override
	public void deleteEntry(IEntry entry) {
	
	}
	
	@Override
	public IConfig getConfig() {
		return null;
	}
	
	@Override
	public void clear() {
	
	}
	
	@Override
	public void backup() {
	
	}
	
	@Override
	public void backup(File folder) {
	
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public File getFile() {
		return file;
	}
	
	@Override
	public Format getFormat() {
		return Format.YML;
	}
}
