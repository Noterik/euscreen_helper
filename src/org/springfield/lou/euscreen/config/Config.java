package org.springfield.lou.euscreen.config;

import java.util.Iterator;

import org.json.simple.JSONObject;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;

public class Config {
	private FsNode configNode;
	private ConfigEnvironment env = ConfigEnvironment.PROD;
	
	public Config() throws SettingNotExistException{
		populateConfigNode();
	}
	
	public Config(ConfigEnvironment env) throws SettingNotExistException{
		this.env = env;
		populateConfigNode();
	}
	
	private void populateConfigNode(){
		this.configNode = Fs.getNode(env.getLocation());
	}
	
	public String getSetting(String name) throws SettingNotExistException{
		try{
			return configNode.getProperty(name);
		}catch(NullPointerException npe){
			throw new SettingNotExistException("Setting " + name + " does not exist!");
		}
	}
	
	public FsNode getSettings() throws SettingNotExistException{
		if(configNode != null){
			return configNode;
		}else{
			throw new SettingNotExistException("There is no configuration.");
		}
	}
	
	public JSONObject getSettingsJSON() throws SettingNotExistException{
		try{
			JSONObject settings = new JSONObject();
			Iterator<String> fields = configNode.getKeys();
			while(fields.hasNext()){
				String field = fields.next();
				settings.put(field, configNode.getProperty(field).trim());
			}
			return settings;
		}catch(NullPointerException npe){
			throw new SettingNotExistException("There is no configuration.");
		}
	}
}
