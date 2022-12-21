package spring.thymeleaf.invoiceTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.thymeleaf.invoiceTracker.model.Invoice;
import spring.thymeleaf.invoiceTracker.service.InvoiceService;



@Controller
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping("/")
	public String getAllInvoice() {		
		return "main";
	}
	@GetMapping("/invoice")
	public String getAllInvoice(Model model) {
		List<Invoice> invoices = invoiceService.findAllInvoice();
		model.addAttribute("invoices", invoices);
		return "allInvoice";
	}
	@GetMapping("/invoice/add")
	public String addInvoice(Model model) {
		Invoice invoice = new Invoice();
		model.addAttribute("invoice", invoice);
		return "addInvoice";
	}

	@PostMapping("/save")
	public String saveInvoice(@ModelAttribute("invoice") Invoice invoice , RedirectAttributes redir) {
		String message = "";
		String err = "error";
		String suc = "success"; 
		if(invoice.getName() == null) {
			message += "Enter name please!\n";
			redir.addFlashAttribute(err,message);
			return "redirect:/invoice/add";
		}			
		else if(invoice.getDate()==null) {
			message += "Enter date please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/invoice/add";
		}			
		else if(invoice.getAmount() == 0.0) {
			message = "Enter amount please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/invoice/add";
		}
		else if(invoice.getLocation() == null) {
			message = "Enter location please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/invoice/add";
		}
		else {
			message = "Success!";			
			invoiceService.updateInvoice(invoice);
			redir.addFlashAttribute(suc, message);
			System.out.println(invoice);
			return "redirect:/invoice";
		}
//		invoiceService.updateInvoice(invoice);
//		return "redirect:/allInvoice";
	}
}
