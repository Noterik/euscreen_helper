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

public class ContentLoader extends ScreenComponent {

	private Map<String, String> componentContents;
	private String page;
	
	public ContentLoader(Screen s, String page) {
		super(s);
		this.page = page;
		populate();
	}
	
	public ContentLoader(Screen s, String target, String page){
		super(s, target);
		this.page = page;
		populate();
	}
	
	
	@JSONField(field= "contents")
	public Map<String, String> getComponentContents(){
		return componentContents;
	}
	
	private void populate(){
		FSList componentNodes = FSListManager.get("/domain/euscreenxl/user/cmsdev/page/" + page + "/component", false);
		List<FsNode> componentList = componentNodes.getNodes();
		componentContents = new HashMap<String, String>();
		for(FsNode node : componentList){
			String id = node.getId();
			String contents = node.getProperty("content");
			componentContents.put(id, contents);
		}
	}

}
