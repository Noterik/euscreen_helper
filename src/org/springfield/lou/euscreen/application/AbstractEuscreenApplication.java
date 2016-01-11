package org.springfield.lou.euscreen.application;

import org.springfield.lou.application.Html5Application;
import org.springfield.lou.euscreen.config.Config;
import org.springfield.lou.euscreen.config.ConfigEnvironment;
import org.springfield.lou.euscreen.config.SettingNotExistException;
import org.springfield.lou.homer.LazyHomer;

public abstract class AbstractEuscreenApplication extends Html5Application implements
		EuscreenApplication {

	private Config config;

	public AbstractEuscreenApplication(String id) {
		super(id);
		try{
			if(super.inDevelopemntMode()){
				config = new Config(ConfigEnvironment.DEVEL);
			}else{
				config = new Config();
			}
		}catch(SettingNotExistException snee){
			snee.printStackTrace();
		}
	}
	
	@Override
	public Config getConfig() {
		return config;
	}

	@Override
	public boolean inDevelMode() {
	    return LazyHomer.inDeveloperMode();
	}
}
