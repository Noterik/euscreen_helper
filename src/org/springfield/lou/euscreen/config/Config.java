package org.springfield.lou.euscreen.config;

import java.util.Iterator;

import org.json.simple.JSONObject;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;

public class Config {
	//The FsNode containing the config for this app. 
	private FsNode configNode;
	
	//Default is PROD environment
	private ConfigEnvironment env = ConfigEnvironment.PROD;
	
	/**
	 * Initializes a new Config object. Populates the Config with populateConfigNode(). 
	 * @throws SettingNotExistException If the config file is not found in the place that's specified inside ConfigEnvironment for the current environment. 
	 */
	public Config() throws SettingNotExistException{
		populateConfigNode();
	}
	
	/**
	 * Allows you to pass the current environment manually. 
	 * @param env The environment for which we want to load the config. 
	 * @throws SettingNotExistException Same as in Config()
	 */
	public Config(ConfigEnvironment env) throws SettingNotExistException{
		this.env = env;
		populateConfigNode();
	}
	
	/**
	 * Gets the configNode from the filesystem using the location specified in the current Environment.  
	 */
	private void populateConfigNode(){
		this.configNode = Fs.getNode(env.getLocation());
	}
	
	/**
	 * Gets a setting specified by String name. In the filesystem this is contained inside the <properties> node for the 
	 * given config. 
	 * 
	 * @param name The name of the setting/property we're trying to load
	 * @return The string value of the setting.
	 * @throws SettingNotExistException If the setting is not defined in the filesystem. 
	 */
	public String getSetting(String name) throws SettingNotExistException{
		try{
			return configNode.getProperty(name);
		}catch(NullPointerException npe){
			throw new SettingNotExistException("Setting " + name + " does not exist!");
		}
	}
	
	/**
	 * 
	 * @return A FsNode with all the settings
	 * @throws SettingNotExistException
	 */
	public FsNode getSettings() throws SettingNotExistException{
		if(configNode != null){
			return configNode;
		}else{
			throw new SettingNotExistException("There is no configuration.");
		}
	}
	
	/**
	 * Returns the JSON representation of all the settings
	 * @return The settings in a JSON object like this: {settingX: "foo", settingY: "bar"}
	 * @throws SettingNotExistException If there is no configuration on the server. 
	 */
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
