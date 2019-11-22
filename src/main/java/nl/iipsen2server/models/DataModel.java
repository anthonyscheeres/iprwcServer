package main.java.nl.iipsen2server.models;

public class DataModel {
	private static ApplicationModel applicationModel;
	private static  HallOfFameModel  hallOfFameModel;
	private static GraveYardModel graveYardModel;
	private static DashboardModel dashboardModel;
	private static RefrigeratorModel refrigeratorModel;
	
	DataModel(){
		
	}
	
	public static ApplicationModel getApplicationModel() {
		
		return applicationModel;
	}
	public static void setApplicationModel(ApplicationModel applicationModel) {
		DataModel.applicationModel = applicationModel;
	}
	public static HallOfFameModel getHallOfFameModel() {
		return hallOfFameModel;
	}
	public static void setHallOfFameModel(HallOfFameModel hallOfFameModel) {
		DataModel.hallOfFameModel = hallOfFameModel;
	}
	public static GraveYardModel getGraveYardModel() {
		return graveYardModel;
	}
	public static void setGraveYardModel(GraveYardModel graveYardModel) {
		DataModel.graveYardModel = graveYardModel;
	}
	public static DashboardModel getDashboardModel() {
		return dashboardModel;
	}
	public static void setDashboardModel(DashboardModel dashboardModel) {
		DataModel.dashboardModel = dashboardModel;
	}
	public static RefrigeratorModel getRefrigeratorModel() {
		return refrigeratorModel;
	}
	public static void setRefrigeratorModel(RefrigeratorModel refrigeratorModel) {
		DataModel.refrigeratorModel = refrigeratorModel;
	}
	
	
	
}
