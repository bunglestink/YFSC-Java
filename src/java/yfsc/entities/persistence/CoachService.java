package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.Coach;

@Service
public class CoachService implements IPersistenceService<Coach> {

    SessionFactory sessionFactory;
    
    public CoachService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Coach> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Coach c order by c.name desc");
        return Collections.checkedList(query.list(), Coach.class);
    }
    
    @Override
    public Coach get(int id) {
        return (Coach)sessionFactory.getCurrentSession().get(Coach.class, id);
    }
    
    @Override
    public void saveOrUpdate(Coach coach) {
        if (coach.getId() == 0) {
            sessionFactory.getCurrentSession().save(coach);
        }
        else {
            sessionFactory.getCurrentSession().update(coach);
        }
    }
    
    @Override
    public void delete(Coach coach) {
        sessionFactory.getCurrentSession().delete(coach);
    }
}
