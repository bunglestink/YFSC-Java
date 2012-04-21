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
	
	@Column(name="AmountPaid")
	private BigDecimal amountPaid;
	
	@OneToOne
	@JoinColumn(name="RegistrationID")
	private AnnualRegistration registration;
	
	@OneToMany(mappedBy="invoice")
	private List<InvoiceItem> invoiceItems;
	
	
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
	
	public BigDecimal getOutstandingBalance() {
		return getTotalCost().subtract(amountPaid);
	}
	
	
	
	
	
	

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
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
}