package com.corona.tracker.models;

public class Stats {
	
	
		
	private String confirmedLocal;
	private String confirmedGlobal;
	private String activeLocal;

	private String dailyLocal;
	
	private String deathsLocal;
	
	
	private String dailyGlobal;
	private String deathsGlobal;
	
	public String getConfirmedLocal() {
		return confirmedLocal;
	}
	public void setConfirmedLocal(String confirmedLocal) {
		this.confirmedLocal = confirmedLocal;
	}
	public String getConfirmedGlobal() {
		return confirmedGlobal;
	}
	public void setConfirmedGlobal(String confirmedGlobal) {
		this.confirmedGlobal = confirmedGlobal;
	}
	public String getActiveLocal() {
		return activeLocal;
	}
	public void setActiveLocal(String activeLocal) {
		this.activeLocal = activeLocal;
	}

	@Override
	public String toString() {
		return "Stats [confirmedLocal=" + confirmedLocal + ", confirmedGlobal=" + confirmedGlobal + ", activeLocal="
				+ activeLocal + ", dailyLocal=" + dailyLocal + ", dailyGlobal=" + dailyGlobal + ", deathsLocal="
				+ deathsLocal + ", deathsGlobal=" + deathsGlobal + "]";
	}
	public String getDailyLocal() {
		return dailyLocal;
	}
	public void setDailyLocal(String dailyLocal) {
		this.dailyLocal = dailyLocal;
	}
	public String getDailyGlobal() {
		return dailyGlobal;
	}
	public void setDailyGlobal(String dailyGlobal) {
		this.dailyGlobal = dailyGlobal;
	}
	public String getDeathsLocal() {
		return deathsLocal;
	}
	public void setDeathsLocal(String deathsLocal) {
		this.deathsLocal = deathsLocal;
	}
	public String getDeathsGlobal() {
		return deathsGlobal;
	}
	public void setDeathsGlobal(String deathsGlobal) {
		this.deathsGlobal = deathsGlobal;
	}
	
	
	
		
}
