package yfsc.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Invoices")
public class Invoice extends EntityObject {
    
	public Invoice() {
		this.invoiceItems = new LinkedList<InvoiceItem>();
	}
	
	@Column(name="InvoiceDate")
	@Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceDate;
	
	@OneToOne
	@JoinColumn(name="RegistrationID")
	private AnnualRegistration registration;
	
	@OneToMany(mappedBy="invoice")
	private List<InvoiceItem> invoiceItems;
	
	@OneToMany(mappedBy="invoice")
	private List<InvoicePayment> invoicePayments;
	
	
	private static SimpleDateFormat dateFormat;
    static {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }
    
    public String getInvoiceDateString() {
        return dateFormat.format(getInvoiceDate());
    }
	
	public boolean isPaidInFull() {
		return getAmountPaid().compareTo(getTotalCost()) >= 0;
	}
	
	public BigDecimal getTotalCost() {
		BigDecimal totalCost = new BigDecimal(0);
		for (InvoiceItem item : invoiceItems) {
			totalCost = totalCost.add(item.getTotalCost());
		}
		return totalCost;
	}
	public BigDecimal getAmountPaid() {
		BigDecimal amountPaid = new BigDecimal(0);
		for (InvoicePayment payment : invoicePayments) {
			amountPaid = amountPaid.add(payment.getAmount());
		}
		return amountPaid;
	}
	
	public BigDecimal getOutstandingBalance() {
		return getTotalCost().subtract(getAmountPaid());
	}
	
	
	
	
	
	

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public AnnualRegistration getRegistration() {
		return registration;
	}

	public void setRegistration(AnnualRegistration registration) {
		this.registration = registration;
	}
	
	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}
	
	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	
	public List<InvoicePayment> getInvoicePayments() {
		return invoicePayments;
	}
	
	public void setInvoicejPayments(List<InvoicePayment> invoicePayments) {
		this.invoicePayments = invoicePayments;
	}
}