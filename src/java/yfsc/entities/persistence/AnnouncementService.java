package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.Announcement;

@Service
public class AnnouncementService implements IPersistenceService<Announcement> {

	private SessionFactory sessionFactory;
	private int currentAnnouncementCount;
    
    public AnnouncementService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
		this.currentAnnouncementCount = 5; // default configuration
    }
    
    @Override
    public List<Announcement> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Announcement a order by a.announcementDate desc");
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
	
	public List<Announcement> listCurrentAnnouncements() {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Announcement a order by a.announcementDate desc")
				.setMaxResults(getCurrentAnnouncementCount());
		return Collections.checkedList(query.list(), Announcement.class);
	}


	// propery methods:
	public int getCurrentAnnouncementCount() {
		return currentAnnouncementCount;
	}

	public void setCurrentAnnouncementCount(int currentAnnouncementCount) {
		this.currentAnnouncementCount = currentAnnouncementCount;
	}	
}
