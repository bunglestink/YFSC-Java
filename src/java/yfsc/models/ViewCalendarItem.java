package yfsc.models;

import yfsc.entities.CalendarItem;

public class ViewCalendarItem extends CalendarItem {

	private int registrationTermId;
	
	public ViewCalendarItem() {
	}
	
	public ViewCalendarItem(CalendarItem item) {
		this.setId(item.getId());
		this.setDays(item.getDays());
		this.setSaturdayNotes(item.getSaturdayNotes());
		this.setSundayNotes(item.getSundayNotes());
		this.setRegistrationTerm(item.getRegistrationTerm());
		
		if (this.getRegistrationTerm() != null) {
			this.setRegistrationTermId(item.getRegistrationTerm().getId());
		}
	}
	
	public CalendarItem toCalendarItem() {
		CalendarItem item = new CalendarItem();
		item.setId(this.getId());
		item.setDays(this.getDays());
		item.setSaturdayNotes(this.getSaturdayNotes());
		item.setSundayNotes(this.getSundayNotes());
		item.setRegistrationTerm(this.getRegistrationTerm());
		
		return item;
	}
	
	
	public int getRegistrationTermId() {
		return registrationTermId;
	}

	public void setRegistrationTermId(int registrationTermId) {
		this.registrationTermId = registrationTermId;
	}
}
