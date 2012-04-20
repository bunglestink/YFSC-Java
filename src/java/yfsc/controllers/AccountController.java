package yfsc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import yfsc.entities.persistence.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired private UserService userService;
	
	
	@RequestMapping("/logOn.do")
	public String logOn(ModelMap model) {
		return "account/logOn";
	}
	
	@RequestMapping("/logOnError.do")
	public String logOnError(ModelMap model) {
		model.addAttribute("error", true);
		return "account/logOn";
	}
	
	
	@RequestMapping("/logOff.do")
	public String logOff() {
		return null;
	}
	
	
	@RequestMapping("/create.do")
	public String create() {
		throw new NotImplementedException();
	}
}
