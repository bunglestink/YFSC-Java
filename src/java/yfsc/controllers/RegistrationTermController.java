package yfsc.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.RegistrationTerm;
import yfsc.entities.persistence.RegistrationTermService;

@Controller
@RequestMapping("/registrationTerm")
public class RegistrationTermController {
    
    RegistrationTermService registrationTermService;
    
    @Autowired
    public RegistrationTermController(RegistrationTermService registrationTermService) {
        this.registrationTermService = registrationTermService;
    }
    
    
    @RequestMapping("/index.do")
    public String index(ModelMap model) {
        
        List<RegistrationTerm> registrations = registrationTermService.list();
        model.addAttribute("registrations", registrations);
		
		return "registrationTerm/index";
    }
    
    
    @RequestMapping("/create.do")
    public String create(ModelMap model) {
        
        RegistrationTerm term = new RegistrationTerm();
        term.setStartDate(new Date());
        term.setEndDate(new Date());
        
        model.addAttribute("model", term);
        return "registrationTerm/edit";
    }
    
    @RequestMapping("/edit.do")
    public String edit(@RequestParam("id") int id, ModelMap model) {
        RegistrationTerm term = registrationTermService.get(id);

        if (term == null) {
            return index(model);
        }
        
        model.addAttribute("model", term);
        return "registrationTerm/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(RegistrationTerm term, ModelMap model) {
        
        registrationTermService.saveOrUpdate(term);
		model.addAttribute("message", "Registration term '" + term.getTermName() + "' saved.");
        return index(model);
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        RegistrationTerm term = registrationTermService.get(id);
        
        if (term == null) {
            return index(model);
        }
        
		if (!term.getCalendarItems().isEmpty()) {
			return "registrationTerm/deleteError";
		}
        
        model.addAttribute("term", term);
        return "registrationTerm/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id, ModelMap model) {
        RegistrationTerm term = registrationTermService.get(id);
        
        if (!term.getCalendarItems().isEmpty()) {
			return "registrationTerm/deleteError";
		}
        
        if (term != null) {
            registrationTermService.delete(term);
			model.addAttribute("message", "Registration term '" + term.getTermName() + "' removed.");
        }
        
        return index(model);
    }
}
