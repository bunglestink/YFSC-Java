package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.Announcement;

@Service
public class AnnouncementService implements IPersistenceService<Announcement> {

    SessionFactory sessionFactory;
    
    public AnnouncementService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Announcement> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Announcement c order by c.announcementDate desc");
        return Collections.checkedList(query.list(), Announcement.class);
    }
    
    @Override
    public Announcement get(int id) {
        return (Announcement)sessionFactory.getCurrentSession().get(Announcement.class, id);
    }
    
    @Override
    public void saveOrUpdate(Announcement announcement) {
        if (announcement.getId() == 0) {
            sessionFactory.getCurrentSession().save(announcement);
        }
        else {
            sessionFactory.getCurrentSession().update(announcement);
        }
    }
    
    @Override
    public void delete(Announcement announcement) {
        sessionFactory.getCurrentSession().delete(announcement);
    }
}
