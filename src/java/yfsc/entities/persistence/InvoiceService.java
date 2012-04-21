package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.Invoice;

@Service
public class InvoiceService implements IPersistenceService<Invoice> {

    SessionFactory sessionFactory;
    
    public InvoiceService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Invoice> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Invoice i order by i.invoiceDate desc");
        return Collections.checkedList(query.list(), Invoice.class);
    }
    
    @Override
    public Invoice get(int id) {
        return (Invoice)sessionFactory.getCurrentSession().get(Invoice.class, id);
    }
    
    @Override
    public void saveOrUpdate(Invoice Invoice) {
        if (Invoice.getId() == 0) {
            sessionFactory.getCurrentSession().save(Invoice);
        }
        else {
            sessionFactory.getCurrentSession().update(Invoice);
        }
    }
    
    @Override
    public void delete(Invoice Invoice) {
        sessionFactory.getCurrentSession().delete(Invoice);
    }
}
