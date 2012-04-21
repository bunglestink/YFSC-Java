package yfsc.controllers;

import java.security.Principal;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yfsc.entities.Invoice;
import yfsc.entities.User;
import yfsc.entities.persistence.InvoiceService;
import yfsc.entities.persistence.UserService;

@Controller
@RequestMapping("/membership")
public class MembershipController {
	
	@Inject UserService userService;
	@Inject InvoiceService invoiceService;
	
	@RequestMapping("/index.do")
	public void index(ModelMap model, Principal principal) {
		User user = userService.get(principal.getName());
		model.addAttribute("user", user);
	}
	
	@RequestMapping("/invoice.do")
	public String invoice(@RequestParam(value="id") int id, @RequestParam(value="format", required=false) String format, ModelMap model) {
		Invoice invoice = invoiceService.get(id);
		if (invoice == null) {
			return "membership/index";
		}
		
		model.addAttribute("invoice", invoice);
		
		if (format != null && format.equals("csv")) {
			return "membership/csvInvoice";
		}
		
		// default is web display
		return "membership/webInvoice";
	}
	
	@RequestMapping("/register.do")
	public void register() {
		
	}
}
