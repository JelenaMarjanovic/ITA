package webshop.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Product> find() {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.createCriteria(Product.class).list();
        return result;
    }

    public Product findByID(int productId) {
        Session session = sessionFactory.getCurrentSession();
        Product result = (Product) session.getNamedQuery("Product.findByProductId").setInteger("productId", productId).uniqueResult();
        return result;
    }

    public List<Product> findByCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.getNamedQuery("Product.findByCategory").setInteger("categoryId", categoryId).list();
        return result;
    }

    public List<Product> findByPage(int page) {
        int perpage = 4;
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.createQuery("from Product").setFirstResult(page * perpage).setMaxResults(perpage).list();
        return result;
    }

    public Long pages() {
        return ((Double) Math.ceil((Long) sessionFactory.getCurrentSession().createQuery("select count(productId) from Product").uniqueResult() / 4.0)).longValue();
    }

    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

}
