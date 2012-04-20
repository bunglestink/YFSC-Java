package yfsc.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yfsc.entities.SkatingSession;

@Controller
@RequestMapping("/skatingSessionsService")
public class SkatingSessionsServiceController {

	@RequestMapping("/current.do")
	public @ResponseBody List<SkatingSession> current() {
		List<SkatingSession> sessions = new LinkedList<SkatingSession>();
		
		SkatingSession s1 = new SkatingSession();
		s1.setId(1);
		s1.setName("Saturday Combined MFD"); 
		s1.setStartDate(new Date()); 
		s1.setDayOfWeek("Saturday"); 
		s1.setStartTime( "8:15 am");
		s1.setEndTime( "9:35 am");
		s1.setTotalCost(new BigDecimal(247)); 
		s1.setWeeksDuration(14);
		s1.setDescription("This is a combine class taking place on Saturdays for 14 weeks.");
		
		SkatingSession s2 = new SkatingSession();
		s2.setId(2); 
		s2.setName("Saturday MFD (30 min)"); 
		s2.setStartDate(new Date()); 
		s2.setDayOfWeek("Saturday"); 
		s2.setStartTime( "9:05 am");
		s2.setEndTime("9:35 am");
		s2.setTotalCost(new BigDecimal(98)); 
		s2.setWeeksDuration(14);
		s2.setDescription("This is a half hour class taking place on Saturdays for 14 weeks.");
				
		SkatingSession s3 = new SkatingSession();
		s3.setId(3); 
		s3.setName("Sunday Bridge Class"); 
		s3.setStartDate(new Date()); 
		s3.setDayOfWeek("Sunday"); 
		s3.setStartTime( "5:10 pm");
		s3.setEndTime( "5:40 pm");
		s3.setTotalCost(new BigDecimal(156)); 
		s3.setWeeksDuration(12);
		s3.setDescription("This bridge class takes place on sunday evenings.");

		sessions.add(s1);
		sessions.add(s2);
		sessions.add(s3);
		
		return sessions;
	}
}
