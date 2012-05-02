package yfsc.controllers;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.Announcement;
import yfsc.entities.persistence.AnnouncementService;

@Controller
@RequestMapping("/announcement")
@PreAuthorize("hasRole('Admin')")
public class AnnouncementController {
    
    @Inject private AnnouncementService announcementService;
    
	
    
    @RequestMapping("/index.do")
    public String index(ModelMap model) {
        
        List<Announcement> announcementes = announcementService.list();
        model.addAttribute("announcements", announcementes);
		
		return "announcement/index";
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
            return index(model);
        }
        
        model.addAttribute("announcement", announcement);
        return "announcement/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(Announcement announcement, ModelMap model) {
        
        announcementService.saveOrUpdate(announcement);
        model.addAttribute("message", "Announcement '" + announcement.getTitle() + "' saved.");
        return index(model);
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        Announcement announcement = announcementService.get(id);
        
        if (announcement == null) {
            return index(model);
        }
        
        model.addAttribute("announcement", announcement);
        return "announcement/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id, ModelMap model) {
        Announcement announcement = announcementService.get(id);
        
        if (announcement != null) {
            announcementService.delete(announcement);
            model.addAttribute("message", "Announcement '" + announcement.getTitle() + "' removed.");
        }
        
        return index(model);
    }
}
