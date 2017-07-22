package com.njdaeger.kernel.core.configuration;

import java.io.File;
import java.util.Collection;
import java.util.List;

public interface IConfig {
	
	IEntry getEntry(String path);
	
	String getString(String path);
	
	double getDouble(String path);
	
	int getInt(String path);
	
	long getLong(String path);
	
	boolean getBoolean(String path);
	
	List<?> getList(String path);
	
	List<String> getStringList(String path);
	
	Object getValue(String path);
	
	Collection<IEntry> getEntryFromValue();
	
	Collection<IEntry> getEntries();
	
	void addEntry(String path, Object value);
	
	void setEntry(String path, Object value);
	
	void deleteEntry(String path);
	
	void deleteEntry(IEntry entry);
	
	IConfig getConfig();
	
	void clear();
	
	void backup();
	
	void backup(File folder);
	
	String getName();
	
	File getFile();
	
	Format getFormat();
	
}
