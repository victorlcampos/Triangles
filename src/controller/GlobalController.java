package controller;

public class GlobalController {
	private GlobalController instance;
	private GlobalController(){}
	
	public GlobalController getInstance() {		
		if(instance == null){
			instance = new GlobalController();
		}		
		return instance;
	}

	public void setInstance(GlobalController instance) {
		this.instance = instance;
	}
	
	
}
