package yfsc.controllers;

import java.util.List;
import javax.inject.Inject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.Coach;
import yfsc.entities.persistence.CoachService;

@Controller
@RequestMapping("/coach")
@PreAuthorize("hasRole('Admin')")
public class CoachController {
    
    @Inject private CoachService coachService;
    
    
    @RequestMapping("/index.do")
    public String index(ModelMap model) {
        
        List<Coach> coaches = coachService.list();
        model.addAttribute("coaches", coaches);
		
		return "coach/index";
    }
    
    
    @RequestMapping("/create.do")
    public String create(ModelMap model) {
        
        Coach coach = new Coach();
        
        model.addAttribute("coach", coach);
        return "coach/edit";
    }
    
    @RequestMapping("/edit.do")
    public String edit(@RequestParam("id") int id, ModelMap model) {
        Coach coach = coachService.get(id);

        if (coach == null) {
            return index(model);
        }
        
        model.addAttribute("coach", coach);
        return "coach/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(Coach coach, ModelMap model) {
        
        coachService.saveOrUpdate(coach);
        model.addAttribute("message", "Coach '" + coach.getName() + "' saved.");
        return index(model);
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        Coach coach = coachService.get(id);
        
        if (coach == null) {
            return index(model);
        }
        
        model.addAttribute("coach", coach);
        return "coach/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id, ModelMap model) {
        Coach coach = coachService.get(id);
        
        if (coach != null) {
            coachService.delete(coach);
            model.addAttribute("message", "Coach '" + coach.getName() + "' saved.");
        }
        
        return index(model);
    }
}
