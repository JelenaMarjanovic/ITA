package webshop.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrderDetailsDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<OrderDetails> find() {
        Session session = sessionFactory.getCurrentSession();
        List<OrderDetails> result = session.createCriteria(OrderDetails.class).list();
        return result;
    }

    public void save(OrderDetails orderDetails) {
        sessionFactory.getCurrentSession().save(orderDetails);
    }

}
