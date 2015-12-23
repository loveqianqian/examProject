package org.marquee.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//TODO 4-1-config
public class Config {
//	private String file;
	private Properties cfg=new Properties();
	public Config(String  file) throws FileNotFoundException, IOException {
//		this.file=file;
		cfg.load(new FileReader(file));
	}
	public String getString(String key){
		return cfg.getProperty(key);
	}
	public int getInt(String key){
		return Integer.parseInt(cfg.getProperty(key));
	}
	public double getDouble(String key){
		return Double.parseDouble(cfg.getProperty(key));
	}
}
