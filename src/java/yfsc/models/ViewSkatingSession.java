package yfsc.models;

import yfsc.entities.SkatingSession;

public class ViewSkatingSession extends SkatingSession {

	private int registrationTermId;

	public ViewSkatingSession() {
		
	}
	
	public ViewSkatingSession(int registrationTermId) {
		this.registrationTermId = registrationTermId;
	}
	
	
	public ViewSkatingSession(SkatingSession skatingSession, int registrationTermId) {
		this.setId(skatingSession.getId());
		this.setName(skatingSession.getName());
		this.setStartDate(skatingSession.getStartDate());
		this.setWeeksDuration(skatingSession.getWeeksDuration());
		this.setDayOfWeek(skatingSession.getDayOfWeek());
		this.setStartTime(skatingSession.getStartTime());
		this.setEndTime(skatingSession.getEndTime());
		this.setTotalCost(skatingSession.getTotalCost());
		this.setDescription(skatingSession.getDescription());
		
		this.registrationTermId = registrationTermId;
	}
	
	
	
	public SkatingSession toSkatingSession() {
		SkatingSession skatingSession = new SkatingSession();
		skatingSession.setId(this.getId());
		skatingSession.setName(this.getName());
		skatingSession.setStartDate(this.getStartDate());
		skatingSession.setWeeksDuration(this.getWeeksDuration());
		skatingSession.setDayOfWeek(this.getDayOfWeek());
		skatingSession.setStartTime(this.getStartTime());
		skatingSession.setEndTime(this.getEndTime());
		skatingSession.setTotalCost(this.getTotalCost());
		skatingSession.setDescription(this.getDescription());
		
		return skatingSession;
	}
	
	public int getRegistrationTermId() {
		return registrationTermId;
	}

	public void setRegistrationTermId(int registrationTermId) {
		this.registrationTermId = registrationTermId;
	}
}
