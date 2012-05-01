package yfsc.controllers;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.RegistrationTerm;
import yfsc.entities.SkatingSession;
import yfsc.entities.persistence.SkatingSessionService;
import yfsc.entities.persistence.RegistrationTermService;
import yfsc.models.ViewSkatingSession;

@Controller
@RequestMapping("/skatingSession")
public class SkatingSessionController {
    
    @Inject private SkatingSessionService skatingSessionService;
	@Inject private RegistrationTermService registrationTermService;
    
    
    
    
    @RequestMapping("/index.do")
    public String index(@RequestParam("id") int id, ModelMap model) {
        
		RegistrationTerm term = registrationTermService.get(id);
        List<SkatingSession> skatingSessions = term.getSessions();
        
		model.addAttribute("skatingSessions", skatingSessions);
		model.addAttribute("registrationTerm", term);
		
		return "skatingSession/index";
    }
    
    
    @RequestMapping("/create.do")
    public String create(@RequestParam("id") int id, ModelMap model) {
        
        ViewSkatingSession skatingSession = new ViewSkatingSession(id);
        
        model.addAttribute("skatingSession", skatingSession);
        return "skatingSession/edit";
    }
    
    @RequestMapping("/edit.do")
    public String edit(@RequestParam("id") int id, ModelMap model) {
        SkatingSession skatingSession = skatingSessionService.get(id);
		RegistrationTerm term = skatingSessionService.getRegistrationTerm(skatingSession);
		
        if (skatingSession == null || term == null) {
            return "redirect:/calendarItem/index.do";
        }
        
		ViewSkatingSession viewSkatingSession = new ViewSkatingSession(skatingSession, term.getId());
        model.addAttribute("skatingSession", viewSkatingSession);
        return "skatingSession/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(ViewSkatingSession skatingSession, ModelMap model) {
        
		if (skatingSession == null || skatingSession.getRegistrationTermId() == 0) {
            return "redirect:/registrationTerm/index.do";
        }
		
		RegistrationTerm term = registrationTermService.get(skatingSession.getRegistrationTermId());
		if (term == null) {
            return index(skatingSession.getRegistrationTermId(), model);
        }
		
		SkatingSession session = skatingSession.toSkatingSession();
		
		if (session.getId() == 0) {
			term.getSessions().add(session);
		}
        skatingSessionService.saveOrUpdate(session);
		registrationTermService.saveOrUpdate(term);
		
		model.addAttribute("message", "Skating session " + skatingSession.getId() + " saved.");
		
        return index(skatingSession.getRegistrationTermId(), model);
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        SkatingSession skatingSession = skatingSessionService.get(id);
        
        if (skatingSession == null) {
            return "redirect:/registrationTerm/index.do";
        }
        
        model.addAttribute("skatingSession", skatingSession);
        return "skatingSession/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id, ModelMap model) {
        SkatingSession skatingSession = skatingSessionService.get(id);
        RegistrationTerm term = skatingSessionService.getRegistrationTerm(skatingSession);
		
        if (skatingSession != null) {
			if (term != null) {
				term.getSessions().remove(skatingSession);
			}
			skatingSessionService.delete(skatingSession);
			model.addAttribute("message", "Skating session " + skatingSession.getId() + " removed.");
        }
        
        return index(term.getId(), model);
    }
}
