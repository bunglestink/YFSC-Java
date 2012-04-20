package yfsc.controllers;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yfsc.businesslogic.RegistrationService;
import yfsc.entities.AnnualRegistration;

@Controller
@RequestMapping("/annualRegistrationService")
public class AnnualRegistrationServiceController {
	
	@Inject private RegistrationService registrationService;
	
	
	
	@RequestMapping("/new.do")
	public @ResponseBody AnnualRegistration newRegistration() {
	
		return new AnnualRegistration();
	}
	
	
	@RequestMapping("/create.do")
	public @ResponseBody Object create(@RequestBody AnnualRegistration registration) {
		// validate model
		
		// return JSON of errors, or true on success
		return false;
	}
	
	
	@RequestMapping("/getCost.do")
	public @ResponseBody String getCost(@RequestBody AnnualRegistration registration) {
		
		BigDecimal totalCost = registrationService.getRegistrationCost(registration);
		NumberFormat usdNumeric = NumberFormat.getCurrencyInstance(Locale.US);
		
		return usdNumeric.format(totalCost.doubleValue());
	}
}
