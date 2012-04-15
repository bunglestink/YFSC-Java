package yfsc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home/*.do")
public class HomeController {
    
    @RequestMapping(method=RequestMethod.GET)
    public void index(@RequestParam(value="name", required=false) String name, ModelMap model) {
        model.addAttribute("name", name);
    }
    
    
    @RequestMapping(method=RequestMethod.GET)
    public void program() {
    }
    
    
    @RequestMapping(method=RequestMethod.GET)
    public void calendar() {
    }
    
    
    @RequestMapping(method=RequestMethod.GET)
    public void clubCoaches() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public void membership() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public void brochure() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public void byLaws() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public void contactInformation() {
    }
}
