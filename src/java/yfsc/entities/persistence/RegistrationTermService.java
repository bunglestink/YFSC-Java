package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.RegistrationTerm;

@Service
public class RegistrationTermService  {

    SessionFactory sessionFactory;
    
    public RegistrationTermService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public List<RegistrationTerm> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from RegistrationTerm r order by r.startDate desc");
        return Collections.checkedList(query.list(), RegistrationTerm.class);
    }
    
    public RegistrationTerm get(int id) {
        return (RegistrationTerm)sessionFactory.getCurrentSession().get(RegistrationTerm.class, id);
    }
}
