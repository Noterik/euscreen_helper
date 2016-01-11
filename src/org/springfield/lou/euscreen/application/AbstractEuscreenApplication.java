package org.springfield.lou.euscreen.application;

import org.springfield.lou.application.Html5Application;
import org.springfield.lou.euscreen.config.Config;
import org.springfield.lou.euscreen.config.ConfigEnvironment;
import org.springfield.lou.euscreen.config.SettingNotExistException;
import org.springfield.lou.homer.LazyHomer;

/**
 * @author david
 * 
 * Overwrites Html5Application with some EUScreen specific functionality. Such as loading a config and being able to 
 * differentiate between production and devel environments inside the code. 
 */
public abstract class AbstractEuscreenApplication extends Html5Application implements
		EuscreenApplication {
	
	private Config config;
	
	/**
	 * Initializes the Application and loads the correct Config for the given environment. 
	 * 
	 * @param id
	 */
	public AbstractEuscreenApplication(String id) {
		super(id);
		try{
			//ugly spelling mistake inside Lou
			if(super.inDevelopemntMode()){
				config = new Config(ConfigEnvironment.DEVEL);
			}else{
				config = new Config();
			}
		}catch(SettingNotExistException snee){
			snee.printStackTrace();
		}
	}
	
	/**
	 * Gets the config for this application.
	 */
	@Override
	public Config getConfig() {
		return config;
	}
	
	/**
	 * @return if we're in development mode or not. 
	 */
	@Override
	public boolean inDevelMode() {
	    return LazyHomer.inDeveloperMode();
	}
}
