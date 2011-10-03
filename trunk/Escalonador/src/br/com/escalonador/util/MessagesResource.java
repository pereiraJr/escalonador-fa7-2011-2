package br.com.escalonador.util;

import java.util.ResourceBundle;

public class MessagesResource {
	
	private static ResourceBundle resource;
	
	private MessagesResource(){
		super();
	}
	
	private static void init() {
		if (resource == null) {
			resource = ResourceBundle.getBundle("br.com.escalonador.resources.messages_pt_BR");
		}
	}
	
	public static String getString(final String key){
		init();
		return resource.getString(key);
	}

}
