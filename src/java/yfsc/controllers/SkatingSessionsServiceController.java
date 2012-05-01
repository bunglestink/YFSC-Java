package yfsc.controllers;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yfsc.entities.RegistrationTerm;
import yfsc.entities.SkatingSession;
import yfsc.entities.persistence.RegistrationTermService;

@Controller
@RequestMapping("/skatingSessionsService")
public class SkatingSessionsServiceController {

	@Inject RegistrationTermService registrationTermService;
			
	
	@RequestMapping("/current.do")
	public @ResponseBody List<SkatingSession> current() {
		
		RegistrationTerm term = registrationTermService.getCurrent();
		
		if (term == null) {
			return new LinkedList<SkatingSession>();
		}
		
		return term.getSessions();
	}
}
