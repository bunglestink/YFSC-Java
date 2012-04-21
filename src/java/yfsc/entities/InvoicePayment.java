package yfsc.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="InvoicePayments")
public class InvoicePayment extends EntityObject {
    
	@Column(name="Description")
	private String description;
	
	@Column(name="Type")
	private String type;
	
	@Column(name="Amount")
	private BigDecimal amount;
	
	@Column(name="DateReceived")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateReceived;
	
	@ManyToOne
	@JoinColumn(name="InvoiceID")
	private Invoice invoice;

	
	private static SimpleDateFormat dateFormat;
    static {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }
    
    public String getDateReceivedString() {
        return dateFormat.format(getDateReceived());
    }
	
	
	
	
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}