package yfsc.controllers;

import java.util.List;
import javax.inject.Inject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.CalendarItem;
import yfsc.entities.RegistrationTerm;
import yfsc.entities.persistence.CalendarItemService;
import yfsc.entities.persistence.RegistrationTermService;
import yfsc.models.ViewCalendarItem;

@Controller
@RequestMapping("/calendarItem")
@PreAuthorize("hasRole('Admin')")
public class CalendarItemController {
    
    @Inject private CalendarItemService calendarItemService;
	@Inject private RegistrationTermService registrationTermService;
    
    
    
    
    @RequestMapping("/index.do")
    public String index(@RequestParam("id") int id, ModelMap model) {
        
		RegistrationTerm term = registrationTermService.get(id);
        List<CalendarItem> calendarItems = term.getCalendarItems();
        
		model.addAttribute("calendarItems", calendarItems);
		model.addAttribute("registrationTerm", term);
		
		return "calendarItem/index";
    }
    
    
    @RequestMapping("/create.do")
    public String create(@RequestParam("id") int id, ModelMap model) {
        
        ViewCalendarItem calendarItem = new ViewCalendarItem();
		calendarItem.setRegistrationTermId(id);
        
        model.addAttribute("calendarItem", calendarItem);
        return "calendarItem/edit";
    }
    
    @RequestMapping("/edit.do")
    public String edit(@RequestParam("id") int id, ModelMap model) {
        CalendarItem calendarItem = calendarItemService.get(id);
		
        if (calendarItem == null) {
            return "redirect:/calendarItem/index.do";
        }
        
		ViewCalendarItem viewItem = new ViewCalendarItem(calendarItem);
        model.addAttribute("calendarItem", viewItem);
        return "calendarItem/edit";
    }
    
    @RequestMapping("/commit.do")
    public String commit(ViewCalendarItem calendarItem, ModelMap model) {
        
		if (calendarItem == null || calendarItem.getRegistrationTermId() == 0) {
            return "redirect:/registrationTerm/index.do";
        }
		
		RegistrationTerm term = registrationTermService.get(calendarItem.getRegistrationTermId());
		if (term == null) {
            return index(calendarItem.getRegistrationTermId(), model);
        }
		
		calendarItem.setRegistrationTerm(term);
        calendarItemService.saveOrUpdate(calendarItem.toCalendarItem());
		model.addAttribute("message", "Calendar item " + calendarItem.getId() + " saved.");
		
        return index(calendarItem.getRegistrationTermId(), model);
    }
    
    @RequestMapping("/deleteConfirm.do")
    public String deleteConfirm(@RequestParam("id") int id, ModelMap model) {
        CalendarItem calendarItem = calendarItemService.get(id);
        
        if (calendarItem == null) {
            return "redirect:/registrationTerm/index.do";
        }
        
        model.addAttribute("calendarItem", calendarItem);
        return "calendarItem/deleteConfirm";
    }
    
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("id") int id, ModelMap model) {
        CalendarItem calendarItem = calendarItemService.get(id);
        
        if (calendarItem != null) {
			calendarItem.getRegistrationTerm().getCalendarItems().remove(calendarItem);
			calendarItemService.delete(calendarItem);
			model.addAttribute("message", "Calendar item " + calendarItem.getId() + " removed.");
        }
        
        return index(calendarItem.getRegistrationTerm().getId(), model);
    }
}
