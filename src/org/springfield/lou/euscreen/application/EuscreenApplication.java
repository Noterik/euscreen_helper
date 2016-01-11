package org.springfield.lou.euscreen.application;

import org.springfield.lou.application.Html5ApplicationInterface;
import org.springfield.lou.euscreen.config.Config;

public interface EuscreenApplication extends Html5ApplicationInterface {
	public Config getConfig();
	public boolean inDevelMode();
}
