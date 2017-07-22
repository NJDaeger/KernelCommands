package com.njdaeger.kernel.core.configuration;

import com.njdaeger.kernel.core.IKernel;

import java.util.Set;

public interface ISection {
	
	Set<String> getKeys(boolean deep);
	
	Set<IEntry> getEntries();
	
	ISection getSection();
	
	String getCurrentPath();
	
	String getName();
	
	IConfig getConfig();
	
	IKernel getKernel();
	
	IEntry getEntry(String path);
	
	ISection getParent();
	
}
