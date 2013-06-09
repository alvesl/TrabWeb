package jsp.trab3;

import java.io.Serializable;

public class AuthBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String resource;
	private String action;
	
	public AuthBean(String resource, String action){
		this.resource = resource;
		this.action = action;
	}
	
	public String getResource(){
		return resource;
	}
	
	public String getAction(){
		return action;
	}
}
