package main.java.nl.iipsen2server.models;

import java.util.List;

class ProjectCollection {
	private List<ExperimentModel> projects;

	public ProjectCollection(List<ExperimentModel> projects) {
		super();
		this.projects = projects;
	}

	public List<ExperimentModel> getProjects() {
		return projects;
	}

	public void setProjects(List<ExperimentModel> projects) {
		this.projects = projects;
	}
	
}
