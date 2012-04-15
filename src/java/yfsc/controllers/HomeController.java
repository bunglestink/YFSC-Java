package yfsc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @RequestMapping("/index.do")
    public void index(@RequestParam(value="name", required=false) String name, ModelMap model) {
        model.addAttribute("name", name);
    }
    
    
    @RequestMapping("/program.do")
    public void program() {
    }
    
    
    @RequestMapping("/calendar.do")
    public void calendar() {
    }
    
    
    @RequestMapping("/clubCoaches.do")
    public void clubCoaches() {
    }
    
    @RequestMapping("/membership.do")
    public void membership() {
    }
    
    @RequestMapping("/brochure.do")
    public void brochure() {
    }
    
    @RequestMapping("/byLaws.do")
    public void byLaws() {
    }
    
    @RequestMapping("/contactInformation.do")
    public void contactInformation() {
    }
}
