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
	int currentTermId;
    
    public RegistrationTermService(SessionFactory sessionFactory, int currentTermId) {
        this.sessionFactory = sessionFactory;
		this.currentTermId = currentTermId;
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
	
	public RegistrationTerm getCurrent() {
		return get(currentTermId);
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
}
