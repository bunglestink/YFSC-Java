package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.SkatingSession;

@Service
public class SkatingSessionService implements IPersistenceService<SkatingSession> {

    SessionFactory sessionFactory;
    
    public SkatingSessionService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<SkatingSession> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from SkatingSession s");
        return Collections.checkedList(query.list(), SkatingSession.class);
    }
    
    @Override
    public SkatingSession get(int id) {
        return (SkatingSession)sessionFactory.getCurrentSession().get(SkatingSession.class, id);
    }
    
    @Override
    public void saveOrUpdate(SkatingSession skatingSession) {
        if (skatingSession.getId() == 0) {
            sessionFactory.getCurrentSession().save(skatingSession);
        }
        else {
            sessionFactory.getCurrentSession().update(skatingSession);
        }
    }
    
    @Override
    public void delete(SkatingSession skatingSession) {
        sessionFactory.getCurrentSession().delete(skatingSession);
    }
}
