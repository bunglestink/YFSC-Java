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
    public void index(ModelMap model) {
        
        List<RegistrationTerm> registrations = registrationTermService.list();
        model.addAttribute("registrations", registrations);
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
            return "redirect:/registrationTerm/index.do";
        }
        
        model.addAttribute("model", term);
        return "registrationTerm/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(RegistrationTerm term) {
        
        registrationTermService.saveOrUpdate(term);
        return "redirect:/registrationTerm/index.do";
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        RegistrationTerm term = registrationTermService.get(id);
        
        if (term == null) {
            return "redirect:/registrationTerm/index.do";
        }
        // TODO: check for calendar items
        
        model.addAttribute("term", term);
        return "registrationTerm/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id) {
        RegistrationTerm term = registrationTermService.get(id);
        
        // TODO check for calendar items before deleting
        
        if (term != null) {
            registrationTermService.delete(term);
        }
        
        return "redirect:/registrationTerm/index.do";
    }
}
