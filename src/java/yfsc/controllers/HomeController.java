package yfsc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.Announcement;
import yfsc.entities.Coach;
import yfsc.entities.persistence.AnnouncementService;
import yfsc.entities.persistence.RegistrationTermService;
import yfsc.entities.persistence.CoachService;

@Controller
@RequestMapping("/home")
public class HomeController {
    
	private AnnouncementService announcementService;
	private CoachService coachService;
	private RegistrationTermService registrationTermService;
	
	@Autowired
	public HomeController(
			AnnouncementService announcementService, 
			CoachService coachService,
			RegistrationTermService regirstrationTermService) {
		
		this.announcementService = announcementService;
		this.coachService = coachService;
		this.registrationTermService = regirstrationTermService;
	}
	
    @RequestMapping("/index.do")
    public String index(ModelMap model) {
		List<Announcement> announcements = announcementService.listCurrentAnnouncements();
		model.addAttribute("announcements", announcements);
		return "home/index";
    }
    
    
    @RequestMapping("/program.do")
    public void program() {
    }
    
    
    @RequestMapping("/calendar.do")
    public void calendar() {
		// TODO: wtf?
    }
    
    
    @RequestMapping("/clubCoaches.do")
    public String clubCoaches(@RequestParam(value="id", required=false) Integer id, ModelMap model) {
		// null id, show all
		if (id == null) {
			List<Coach> coaches = coachService.list();
			model.addAttribute("coaches", coaches);
			return "home/coaches";
		}
		
		// not null, single coach
		Coach coach = coachService.get(id);
		if (coach == null) {
			return index(model);
		}
		model.addAttribute("coach", coach);
		return "home/coach";
    }
    
    @RequestMapping("/membership.do")
    public void membership() {
    }
    
    @RequestMapping("/brochure.do")
    public void brochure() {
    }
    
    @RequestMapping("/byLaws.do")
    public void byLaws() {
    }
    
    @RequestMapping("/contactInformation.do")
    public void contactInformation() {
    }
}
