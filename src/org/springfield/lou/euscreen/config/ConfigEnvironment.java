package org.springfield.lou.euscreen.config;

public enum ConfigEnvironment {
	
	DEVEL("/domain/euscreenxl/config/config_devel"),
	PROD("/domain/euscreenxl/config/config_prod");
	
	private String location;
	
	ConfigEnvironment(String location){
		this.location = location;
	}
	
	public String getLocation(){
		return this.location;
	}
}
