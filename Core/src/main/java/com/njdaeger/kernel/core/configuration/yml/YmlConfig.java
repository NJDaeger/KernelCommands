package com.njdaeger.kernel.core.configuration.yml;

import com.njdaeger.kernel.core.configuration.Format;
import com.njdaeger.kernel.core.configuration.IConfig;
import com.njdaeger.kernel.core.configuration.IEntry;

import java.io.File;
import java.util.Collection;
import java.util.List;

public final class YmlConfig implements IConfig {
	
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
		return null;
	}
	
	@Override
	public File getFile() {
		return null;
	}
	
	@Override
	public Format getFormat() {
		return Format.YML;
	}
}
