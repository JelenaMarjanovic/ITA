package webshop.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrdersDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Orders> find() {
        Session session = sessionFactory.getCurrentSession();
        List<Orders> result = session.createCriteria(Orders.class).list();
        return result;
    }

    public void save(Orders order) {
        sessionFactory.getCurrentSession().save(order);
    }

}
