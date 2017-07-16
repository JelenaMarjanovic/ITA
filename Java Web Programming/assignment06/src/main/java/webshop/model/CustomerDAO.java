package webshop.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Customer> find() {
        Session session = sessionFactory.getCurrentSession();
        List<Customer> result = session.createCriteria(Customer.class).list();
        return result;
    }

    public Customer findByID(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        Customer result = (Customer) session.getNamedQuery("Customer.findByCustomerId").setInteger("customerId", customerId).uniqueResult();
        return result;
    }

    public List<Customer> findByPage(int page) {
        int perpage = 10;
        Session session = sessionFactory.getCurrentSession();
        List<Customer> result = session.createQuery("from Customer").setFirstResult(page * perpage).setMaxResults(perpage).list();
        return result;
    }

    public Long pages() {
        return ((Double) Math.ceil((Long) sessionFactory.getCurrentSession().createQuery("select count(customerId) from Customer").uniqueResult() / 4.0)).longValue();
    }

    public void save(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }

    public void update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }

    public void delete(Customer customer) {
        sessionFactory.getCurrentSession().delete(customer);
    }

}
