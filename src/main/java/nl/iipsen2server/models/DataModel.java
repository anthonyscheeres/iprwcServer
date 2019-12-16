package nl.iipsen2server.models;

public class DataModel {
	private static ApplicationModel applicationModel;
	
	
	DataModel(){
		
	}


	public static ApplicationModel getApplicationModel() {
		return applicationModel;
	}


	public static void setApplicationModel(ApplicationModel applicationModel) {
		DataModel.applicationModel = applicationModel;
	}
	
	
}
