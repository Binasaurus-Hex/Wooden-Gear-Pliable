package game;

import java.util.HashMap;
import java.util.Map;

public class Key {
	private Map<String,Boolean> keys;

	public Key() {
		keys = new HashMap<String,Boolean>();
		keys.put("I", false);
		keys.put("K", false);
		keys.put("J", false);
		keys.put("L", false);
		keys.put("W", false);
		keys.put("A", false);
		keys.put("S", false);
		keys.put("D", false);
	}
	
	public boolean isKeyDown(String key){
		return keys.get(key);
	}
	
	public void setKeyDown(String key){
		keys.put(key, true);
	}
	
	public void setKeyUp(String key){
		keys.put(key, false);
	}

}
