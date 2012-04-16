package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.CalendarItem;

@Service
public class CalendarItemService implements IPersistenceService<CalendarItem> {

    SessionFactory sessionFactory;
    
    public CalendarItemService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
    @Override
    public List<CalendarItem> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from CalendarItem c order by c.calendarItemDate desc");
        return Collections.checkedList(query.list(), CalendarItem.class);
    }
    
    @Override
    public CalendarItem get(int id) {
        return (CalendarItem)sessionFactory.getCurrentSession().get(CalendarItem.class, id);
    }
    
    @Override
    public void saveOrUpdate(CalendarItem calendarItem) {
        if (calendarItem.getId() == 0) {
            sessionFactory.getCurrentSession().save(calendarItem);
        }
        else {
            sessionFactory.getCurrentSession().update(calendarItem);
        }
    }
    
    @Override
    public void delete(CalendarItem calendarItem) {
        sessionFactory.getCurrentSession().delete(calendarItem);
    }
}
