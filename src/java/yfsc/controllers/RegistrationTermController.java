package yfsc.controllers;

import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.RegistrationTerm;
import yfsc.entities.persistence.HibernateUtil;
import yfsc.entities.persistence.RegistrationTermService;

@Controller
@RequestMapping("/registrationTerm")
public class RegistrationTermController {
    
    Session session;
    RegistrationTermService registrationTermService;
    
    public RegistrationTermController() {
        session = HibernateUtil.getSessionFactory().openSession();
        registrationTermService = new RegistrationTermService(session);
    }
    
    
    @RequestMapping("/index.do")
    public void index(ModelMap model) {
        
        List<RegistrationTerm> registrations = registrationTermService.list();
        model.addAttribute("registrations", registrations);
    }
    
    
    @RequestMapping("/create.do")
    public void create() {
        
    }
    
    @RequestMapping("/edit.do")
    public void edit(@RequestParam("ID") int ID) {
        
    }
    
    @RequestMapping("/commit.do")
    public void commit() {
        
    }
    
    @RequestMapping("/deleteConfirm.do")
    public void deleteConfirm(@RequestParam("ID") int ID) {
        
    }
    
    @RequestMapping("/delete.do")
    public void delete(@RequestParam("ID") int ID) {
        
    }
}
