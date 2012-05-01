package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.RegistrationTerm;

@Service
public class RegistrationTermService implements IPersistenceService<RegistrationTerm> {

    SessionFactory sessionFactory;
    
    public RegistrationTermService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<RegistrationTerm> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from RegistrationTerm r order by r.startDate desc");
        return Collections.checkedList(query.list(), RegistrationTerm.class);
    }
    
    @Override
    public RegistrationTerm get(int id) {
        return (RegistrationTerm)sessionFactory.getCurrentSession().get(RegistrationTerm.class, id);
    }
    
    @Override
    public void saveOrUpdate(RegistrationTerm term) {
        if (term.getId() == 0) {
            sessionFactory.getCurrentSession().save(term);
        }
        else {
            sessionFactory.getCurrentSession().update(term);
        }
    }
    
    @Override
    public void delete(RegistrationTerm term) {
        sessionFactory.getCurrentSession().delete(term);
    }
	
	
	public RegistrationTerm getCurrent() {
		Query query = sessionFactory.getCurrentSession().createQuery("from RegistrationTerm r where r.current = 't'");
		List<RegistrationTerm> terms = Collections.checkedList(query.list(), RegistrationTerm.class);
		if (terms.size() != 1) {
			return null;
		}
		return terms.get(0);
	}
	
	public void setCurrent(RegistrationTerm term) {
		Query query = sessionFactory.getCurrentSession().createQuery("from RegistrationTerm");
		List<RegistrationTerm> terms = Collections.checkedList(query.list(), RegistrationTerm.class);
		
		for (RegistrationTerm t : terms) {
			t.setCurrent(false);
		}
		term.setCurrent(true);
	}
}
