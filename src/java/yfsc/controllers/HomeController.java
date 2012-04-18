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
    
	@Autowired private AnnouncementService announcementService;
	@Autowired private CoachService coachService;
	@Autowired private RegistrationTermService registrationTermService;
	
	
    @RequestMapping("/index.do")
    public String index(ModelMap model) {
		List<Announcement> announcements = getAnnouncementService().listCurrentAnnouncements();
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
			List<Coach> coaches = getCoachService().list();
			model.addAttribute("coaches", coaches);
			return "home/coaches";
		}
		
		// not null, single coach
		Coach coach = getCoachService().get(id);
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


	
	
	
	public AnnouncementService getAnnouncementService() {
		return announcementService;
	}

	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	public CoachService getCoachService() {
		return coachService;
	}

	public void setCoachService(CoachService coachService) {
		this.coachService = coachService;
	}

	public RegistrationTermService getRegistrationTermService() {
		return registrationTermService;
	}

	public void setRegistrationTermService(RegistrationTermService registrationTermService) {
		this.registrationTermService = registrationTermService;
	}
}
