package yfsc.controllers;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yfsc.businesslogic.RegistrationService;
import yfsc.entities.AnnualRegistration;

@Controller
@RequestMapping("/annualRegistrationService")
public class AnnualRegistrationServiceController {
	
	@Autowired private RegistrationService registrationService;
	
	@RequestMapping("/new.do")
	public @ResponseBody AnnualRegistration newRegistration() {
		return new AnnualRegistration();
	}
	
	@RequestMapping("/create.do")
	public @ResponseBody Object create(AnnualRegistration registration) {
		// validate model
		
		// return JSON of errors, or true on success
		return false;
	}
	
	@RequestMapping("/getCost.do")
	public @ResponseBody BigDecimal getCost(AnnualRegistration registration) {
		
		return registrationService.getRegistrationCost(registration);
	}
}
