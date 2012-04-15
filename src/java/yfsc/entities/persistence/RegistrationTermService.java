package yfsc.entities.persistence;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import yfsc.entities.RegistrationTerm;


public class RegistrationTermService  {

    Session session;
    
    public RegistrationTermService(Session session) {
        this.session = session;
    }
    
    public List<RegistrationTerm> list() {
        Query query = session.createQuery("from RegistrationTerm r order by r.StartDate desc");
        return query.list();
    }
}
