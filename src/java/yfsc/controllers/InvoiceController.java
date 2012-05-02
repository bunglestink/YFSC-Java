package yfsc.controllers;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yfsc.entities.Invoice;
import yfsc.entities.InvoicePayment;
import yfsc.entities.persistence.InvoicePaymentService;
import yfsc.entities.persistence.InvoiceService;

@Controller
@RequestMapping("/invoice")
@PreAuthorize("hasRole('Admin')")
public class InvoiceController {
    
    @Inject private InvoiceService invoiceService;
	@Inject private InvoicePaymentService invoicePaymentService;
    
    
    @RequestMapping("/index.do")
    public String index(ModelMap model) {
        
        List<Invoice> invoices = invoiceService.list();
        model.addAttribute("invoices", invoices);
		
		return "invoice/index";
    }
 
	@RequestMapping("/view.do")
	public String view(@RequestParam(value="id") int id, ModelMap model) {
		
		Invoice invoice = invoiceService.get(id);
		if (invoice == null) {
			return "invoice/index";
		}
		
		model.addAttribute("invoice", invoice);
		return "invoice/view";
	}
	
	@RequestMapping("/addPayment.do")
	@ResponseBody
	public Object addPayment(@RequestBody InvoicePayment invoicePayment) {
		
		Invoice invoiceById = invoicePayment.getInvoice();
		if (invoiceById == null) {
			return false;
		}
		
		Invoice invoice = invoiceService.get(invoiceById.getId());
		if (invoice == null) {
			return false;
		}
		
		invoicePayment.setDateReceived(new Date());
		invoicePayment.setInvoice(invoice);
		invoicePaymentService.saveOrUpdate(invoicePayment);
		return invoicePayment.getId();
	} 
	
	@RequestMapping("/deletePayment.do")
	@ResponseBody
	public boolean deletePayment(@RequestParam(value="id") int id, ModelMap model) {
		
		InvoicePayment invoicePayment = invoicePaymentService.get(id);
		if (invoicePayment == null) {
			return false;
		}
		invoicePaymentService.delete(invoicePayment);
		return true;
	}
}
