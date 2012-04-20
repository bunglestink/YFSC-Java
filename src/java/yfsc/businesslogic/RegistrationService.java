package yfsc.businesslogic;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import yfsc.entities.AnnualRegistration;
import yfsc.entities.AnnualRegistrationSkater;
import yfsc.entities.SkatingSession;


@Service
public class RegistrationService {
	
	public RegistrationService() {
		
	}
	
	
	
	public void submitRegistration(AnnualRegistration registration) {
		throw new NotImplementedException();
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
	
	
	private BigDecimal getSessionCost(AnnualRegistration registration) {
		
		BigDecimal totalCost = new BigDecimal(0);
		
		for (AnnualRegistrationSkater skater : registration.getSkaters()) {
			for (SkatingSession session : skater.getSessions()) {
				totalCost = totalCost.add(session.getTotalCost());
			}
		}
		
		return totalCost;
	}
}
