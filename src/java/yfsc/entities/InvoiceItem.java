package yfsc.entities;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name="InvoiceItems")
public class InvoiceItem extends EntityObject {
    
	@Column(name="Description")
	private String description;
	
	@Column(name="UnitCost")
	private BigDecimal unitCost;
	
	@Column(name="Quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="InvoiceID")
	private Invoice invoice;

	public BigDecimal getTotalCost() {
		return getUnitCost().multiply(new BigDecimal(getQuantity()));
	}
	
	
	
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}