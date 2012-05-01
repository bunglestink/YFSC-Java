package yfsc.businesslogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.AnnualRegistration;
import yfsc.entities.AnnualRegistrationSkater;
import yfsc.entities.Invoice;
import yfsc.entities.InvoiceItem;
import yfsc.entities.SkatingSession;
import yfsc.entities.User;
import yfsc.entities.persistence.InvoiceService;
import yfsc.entities.persistence.RegistrationTermService;
import yfsc.entities.persistence.SkatingSessionService;


@Service
public class RegistrationService {
	
	private SessionFactory sessionFactory;
	private InvoiceService invoiceService;
	private SkatingSessionService skatingSessionService;
	private RegistrationTermService registrationTermService;
	
	public RegistrationService(SessionFactory sessionFactory, InvoiceService invoiceService, 
			SkatingSessionService skatingSessionService, RegistrationTermService registrationTermService) {
		this.sessionFactory = sessionFactory;
		this.invoiceService = invoiceService;
		this.skatingSessionService = skatingSessionService;
		this.registrationTermService = registrationTermService;
	}
	
	
	
	public AnnualRegistration createNewRegistration() {
		return new AnnualRegistration();
	}
	
	public void submitRegistration(AnnualRegistration registration, User user) {
		Session session = sessionFactory.getCurrentSession();
		
		registration.setUser(user);
		registration.setRegistrationTerm(registrationTermService.getCurrent());
		
		// swap JSON skating sessions for actual entities
		for (AnnualRegistrationSkater skater : registration.getSkaters()) {
			List<SkatingSession> sessions = skater.getSessions();
			List<SkatingSession> sessionEntities = new LinkedList<SkatingSession>();
			skater.setSessions(sessionEntities);
			
			for (SkatingSession skatingSession : sessions) {
				sessionEntities.add(skatingSessionService.get(skatingSession.getId()));
			}
		}
		
		// save registration and skaters
		session.save(registration);
		for (AnnualRegistrationSkater skater : registration.getSkaters()) {
			session.save(skater);
		}
		
		generateInvoice(registration);
	}
	
	
	private void generateInvoice(AnnualRegistration registration) {
		Invoice invoice = invoiceService.create(registration);
		List<InvoiceItem> items = invoice.getInvoiceItems();
		items.addAll(getMembershipCostItems(registration));
		items.addAll(getHomeClubCostItems(registration));
		items.addAll(getSessionCostItems(registration));
		
		registration.setInvoice(invoice);
		invoiceService.saveOrUpdate(invoice);
		for (InvoiceItem item : items) {
			item.setInvoice(invoice);
			sessionFactory.getCurrentSession().save(item);
		}
	}
	
	
	
	public BigDecimal getRegistrationCost(AnnualRegistration registration) {
		BigDecimal membershipCost = getMembershipCost(registration);
		BigDecimal homeClubCost = getHomeClubCost(registration);
		BigDecimal sessionCost = getSessionCost(registration);
		
		return membershipCost
				.add(homeClubCost)
				.add(sessionCost);
	}
	
	
	private BigDecimal getMembershipCost(AnnualRegistration registration) {
		
		// Yale Affiliated: $20/member
		// Non-Yale: $30 first, $20 additional
		int skaters = registration.getSkaters().size();

		return new BigDecimal(
			registration.isYaleAffiliation() ?
				skaters * 20 :
				skaters == 0 ? 
					0 : 
					(skaters * 20) + 10);
	}
	
	private List<InvoiceItem> getMembershipCostItems(AnnualRegistration registration) {
		// Yale Affiliated: $20/member
		// Non-Yale: $30 first, $20 additional
		List<InvoiceItem> items = new LinkedList<InvoiceItem>();
		int skaterCount = registration.getSkaters().size();
		
		if (skaterCount == 0) {
			return items;
		}
		if (registration.isYaleAffiliation()) {
			InvoiceItem item = new InvoiceItem();
			item.setDescription("Skater Memebership: Yale affiliated");
			item.setQuantity(skaterCount);
			item.setUnitCost(new BigDecimal(20));
			items.add(item);
		}
		else {
			InvoiceItem item = new InvoiceItem();
			item.setDescription("Skater Memebership: first non-Yale affiliated");
			item.setQuantity(1);
			item.setUnitCost(new BigDecimal(30));
			items.add(item);
			
			if (skaterCount > 1) {
				InvoiceItem addItem = new InvoiceItem();
				addItem.setDescription("Skater Memebership: additional non-Yale affiliated");
				addItem.setQuantity(skaterCount - 1);
				addItem.setUnitCost(new BigDecimal(20));
				items.add(addItem);
			}
		}
		return items;
	}
	
	
	private BigDecimal getHomeClubCost(AnnualRegistration registration) {
		
		// Basic: $12 each, $12 for SnoplowSam parent
		// Regular: $50 first, $20 additional

		int total = 0;
		boolean firstRegular = true;

		for (AnnualRegistrationSkater skater : registration.getSkaters()) {
			String level = skater.getLevel();
			if (level != null && level.toLowerCase().equals("basic")) {
				total += 12;
			}
			else if (firstRegular) {
				total += 50;
				firstRegular = false;
			}
			else {
				total += 20;
			}
		}

		return new BigDecimal(total);
	}
	
	private List<InvoiceItem> getHomeClubCostItems(AnnualRegistration registration) {
		// Basic: $12 each, $12 for SnoplowSam parent
		// Regular: $50 first, $20 additional
		List<InvoiceItem> items = new LinkedList<InvoiceItem>();
		int basicSkaterCount = 0;
		int regularSkaterCount = 0;
		
		for(AnnualRegistrationSkater skater : registration.getSkaters()) {
			if (skater.getLevel().toLowerCase().equals("basic")) {
				basicSkaterCount++;
			}
			else { 
				regularSkaterCount++;
			}
		}
		
		if (basicSkaterCount > 0) {
			InvoiceItem item = new InvoiceItem();
			item.setDescription("Basic skater / SnoplowSam parent");
			item.setQuantity(basicSkaterCount);
			item.setUnitCost(new BigDecimal(12));
			items.add(item);
		}
		if (regularSkaterCount > 0) {
			InvoiceItem item = new InvoiceItem();
			item.setDescription("Regular skater: first in family");
			item.setQuantity(1);
			item.setUnitCost(new BigDecimal(50));
			items.add(item);
		}
		if (regularSkaterCount > 1) {
			InvoiceItem item = new InvoiceItem();
			item.setDescription("Regular skater: additional");
			item.setQuantity(regularSkaterCount - 1);
			item.setUnitCost(new BigDecimal(20));
			items.add(item);
		}
		
		return items;
	}
	
	
	private BigDecimal getSessionCost(AnnualRegistration registration) {
		
		BigDecimal totalCost = new BigDecimal(0);
		
		for (AnnualRegistrationSkater skater : registration.getSkaters()) {
			for (SkatingSession session : skater.getSessions()) {
				totalCost = totalCost.add(session.getTotalCost());
			}
		}
		
		return totalCost;
	}
	
	private List<InvoiceItem> getSessionCostItems(AnnualRegistration registration) {
		Map<SkatingSession, Integer> sessionCosts = new HashMap<SkatingSession, Integer>();
		List<InvoiceItem> items = new LinkedList<InvoiceItem>();
		
		for (AnnualRegistrationSkater skater : registration.getSkaters()) {
			for (SkatingSession skatingSession : skater.getSessions()) {
				if (sessionCosts.containsKey(skatingSession)) {
					sessionCosts.put(skatingSession, sessionCosts.get(skatingSession) + 1);
				}
				else {
					sessionCosts.put(skatingSession, 1);
				}
			}
		}
		
		for (SkatingSession key : sessionCosts.keySet()) {
			InvoiceItem item = new InvoiceItem();
			item.setDescription("Skating session: " + key.getDescription());
			item.setQuantity(sessionCosts.get(key));
			item.setUnitCost(key.getTotalCost());
			items.add(item);
		}
		
		return items;
	}
}
