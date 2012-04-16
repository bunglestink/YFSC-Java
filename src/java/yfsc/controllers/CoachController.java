package yfsc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.Coach;
import yfsc.entities.persistence.CoachService;

@Controller
@RequestMapping("/coach")
public class CoachController {
    
    CoachService coachService;
    
    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }
    
    
    @RequestMapping("/index.do")
    public void index(ModelMap model) {
        
        List<Coach> coaches = coachService.list();
        model.addAttribute("coaches", coaches);
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
            return "redirect:/coach/index.do";
        }
        
        model.addAttribute("coach", coach);
        return "coach/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(Coach coach) {
        
        coachService.saveOrUpdate(coach);
        // TODO: "tempdata" message
        return "redirect:/coach/index.do";
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        Coach coach = coachService.get(id);
        
        if (coach == null) {
            return "redirect:/coach/index.do";
        }
        
        model.addAttribute("coach", coach);
        return "coach/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id) {
        Coach coach = coachService.get(id);
        
        if (coach != null) {
            coachService.delete(coach);
            // TODO: "tempdata" message
        }
        
        return "redirect:/coach/index.do";
    }
}
