package org.springfield.lou.euscreen.contentloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springfield.fs.FSList;
import org.springfield.fs.FSListManager;
import org.springfield.fs.FsNode;
import org.springfield.lou.json.JSONField;
import org.springfield.lou.screen.Screen;
import org.springfield.lou.screencomponent.component.ScreenComponent;

/**
 * This class loads the data for a given page from Smithers/Bart and sends it to the client. In the client components can listen 
 * to changes to this component by doing eddie.getComponent('contentloader').on('contents-changed').
 * 
 * @author david
 *
 */
public class ContentLoader extends ScreenComponent {
	
	//This array contains all the data for the components on the page like this {carousel: "<CONTENTS HERE >", compX: "<CONTENTS HERE>"};
	private Map<String, String> componentContents;
	
	//The id of the page that we're trying to load, this is mapped to the filesystem like /user/domain/euscreenxl/user/cms/<page>
	private String page;
	
	/**
	 * Same as ScreenComponent(Screen s) with the addition of the String of the page identifier we're trying to load. 
	 * @param s The screen we're rendering the ContentLoader on. 
	 * @param page The page identifier so we can get the correct page from the filesystem. 
	 */
	public ContentLoader(Screen s, String page) {
		super(s);
		this.page = page;
		populate();
	}
	
	/**
	 * Same as ScreenComponent(Screen s) with the addition of having a client side target identifier to render the component inside in.
	 * Not really relevant for this specific component, but still implemented.
	 *  
	 * @param s
	 * @param target
	 * @param page
	 */
	public ContentLoader(Screen s, String target, String page){
		super(s, target);
		this.page = page;
		populate();
	}
	
	/**
	 * Returns the componentContents variable. 
	 * 
	 * @return The map of component contents like {carousel: "<CONTENTS HERE >", compX: "<CONTENTS HERE>"}
	 */
	@JSONField(field= "contents")
	public Map<String, String> getComponentContents(){
		return componentContents;
	}
	
	/**
	 * Populates the componentContents variable. 
	 */
	private void populate(){
		//Do a request to smithers.
		FSList componentNodes = FSListManager.get("/domain/euscreenxl/user/cmsdev/page/" + page + "/component", false);
		List<FsNode> componentList = componentNodes.getNodes();
		componentContents = new HashMap<String, String>();
		
		//Iterate over all the nodes 
		for(FsNode node : componentList){
			String id = node.getId();
			String contents = node.getProperty("content");
			componentContents.put(id, contents);
		}
	}

}
