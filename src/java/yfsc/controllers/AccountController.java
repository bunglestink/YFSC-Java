package yfsc.controllers;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yfsc.entities.User;
import yfsc.entities.persistence.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Inject private UserService userService;
	
	
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
	public String create(ModelMap model) {
		model.addAttribute("user", new User());
		
		return "/account/create";
	}
	
	@RequestMapping(value="/createSubmit.do", method= RequestMethod.POST)
	public String createSubmit(@Valid User user, BindingResult result, ModelMap model) {
		
		List<String> errors = new LinkedList<String>();
		
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getField() + " " + error.getDefaultMessage());
			}
		}
		if (!userService.isUsernameAvailable(user.getUsername())) {
			errors.add("Username '" + user.getUsername() + "' is not available.  Please choose new username.");
		}
		if (!errors.isEmpty()) {
			model.addAttribute("user", user);
			model.addAttribute("errors", errors);
			return "/account/create";
		}
		
		
		try {
			userService.createUserAccount(user);
		}
		catch (Exception e) {
			errors.add("Internal error creating user account.  Please try again later.");
			model.addAttribute("user", user);
			model.addAttribute("errors", errors);
			return "/account/create";
		}
		
		userService.loginUser(user);
		return "redirect:/";
	}
}
