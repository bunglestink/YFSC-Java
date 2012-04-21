package yfsc.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;
import yfsc.businesslogic.RegistrationService;
import yfsc.entities.AnnualRegistration;
import yfsc.entities.User;
import yfsc.entities.persistence.UserService;

@Controller
@RequestMapping("/annualRegistrationService")
public class AnnualRegistrationServiceController {
	
	@Inject private RegistrationService registrationService;
	@Inject private UserService userService;
	@Inject private ModelAndView jsonModelAndView;
	
	
	@RequestMapping("/new.do")
	@ResponseBody
	public AnnualRegistration newRegistration() {
	
		return registrationService.createNewRegistration();
	}
	
	
	@RequestMapping("/create.do")
	@ResponseBody
	public Object create(@Valid @RequestBody AnnualRegistration registration, Principal principal) {
		
		User user = userService.get(principal.getName());
 		registrationService.submitRegistration(registration, user);
		return true;
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException errors) {
		List<String> errorList = new LinkedList<String>();
		BindingResult bindingResult = errors.getBindingResult();
		
		for (FieldError error : bindingResult.getFieldErrors()) {
			errorList.add(error.getField() + " " + error.getDefaultMessage());
		}
		
		jsonModelAndView.addObject("errors", errorList);
		return jsonModelAndView;
	}
	
	
	@RequestMapping("/getCost.do")
	public @ResponseBody String getCost(@RequestBody AnnualRegistration registration) {
		
		BigDecimal totalCost = registrationService.getRegistrationCost(registration);
		NumberFormat usdNumeric = NumberFormat.getCurrencyInstance(Locale.US);
		
		return usdNumeric.format(totalCost.doubleValue());
	}
}
