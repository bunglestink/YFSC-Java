package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import yfsc.entities.InvoicePayment;

@Service
public class InvoicePaymentService implements IPersistenceService<InvoicePayment> {

    SessionFactory sessionFactory;
    
    public InvoicePaymentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<InvoicePayment> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from InvoicePayment i order by i.dateReceived desc");
        return Collections.checkedList(query.list(), InvoicePayment.class);
    }
    
    @Override
    public InvoicePayment get(int id) {
        return (InvoicePayment)sessionFactory.getCurrentSession().get(InvoicePayment.class, id);
    }
    
    @Override
    public void saveOrUpdate(InvoicePayment invoicePayment) {
        if (invoicePayment.getId() == 0) {
            sessionFactory.getCurrentSession().save(invoicePayment);
        }
        else {
            sessionFactory.getCurrentSession().update(invoicePayment);
        }
    }
    
    @Override
    public void delete(InvoicePayment invoicePayment) {
        sessionFactory.getCurrentSession().delete(invoicePayment);
    }
}
