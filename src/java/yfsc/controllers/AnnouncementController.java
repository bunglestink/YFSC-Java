package yfsc.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.Announcement;
import yfsc.entities.persistence.AnnouncementService;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    
    AnnouncementService announcementService;
    
    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }
    
    
    @RequestMapping("/index.do")
    public void index(ModelMap model) {
        
        List<Announcement> announcementes = announcementService.list();
        model.addAttribute("announcements", announcementes);
    }
    
    
    @RequestMapping("/create.do")
    public String create(ModelMap model) {
        
        Announcement announcement = new Announcement();
		announcement.setAnnouncementDate(new Date());
        
        model.addAttribute("announcement", announcement);
        return "announcement/edit";
    }
    
    @RequestMapping("/edit.do")
    public String edit(@RequestParam("id") int id, ModelMap model) {
        Announcement announcement = announcementService.get(id);

        if (announcement == null) {
            return "redirect:/announcement/index.do";
        }
        
        model.addAttribute("announcement", announcement);
        return "announcement/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(Announcement announcement) {
        
        announcementService.saveOrUpdate(announcement);
        // TODO: "tempdata" message
        return "redirect:/announcement/index.do";
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        Announcement announcement = announcementService.get(id);
        
        if (announcement == null) {
            return "redirect:/announcement/index.do";
        }
        
        model.addAttribute("announcement", announcement);
        return "announcement/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id) {
        Announcement announcement = announcementService.get(id);
        
        if (announcement != null) {
            announcementService.delete(announcement);
            // TODO: "tempdata" message
        }
        
        return "redirect:/announcement/index.do";
    }
}
