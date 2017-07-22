package com.njdaeger.kernel.core.configuration;

import java.util.List;

public interface IEntry {
	
	String getString();
	
	double getDouble();
	
	int getInt();
	
	long getLong();
	
	boolean getBoolean();
	
	List<?> getList();
	
	List<String> getStringList();
	
	String getPath();
	
	Object getValue();
	
	IEntry setPath(String path);
	
	String getName();
	
	void delete();
	
}
