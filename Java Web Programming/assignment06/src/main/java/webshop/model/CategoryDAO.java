package webshop.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Category> find() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> result = session.createCriteria(Category.class).list();
        return result;
    }

    public Category findByID(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Category result = (Category) session.getNamedQuery("Category.findByCategoryId").setInteger("categoryId", categoryId).uniqueResult();
        return result;
    }

    public void save(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    public void update(Category category) {
        sessionFactory.getCurrentSession().update(category);
    }

    public void delete(Category category) {
        sessionFactory.getCurrentSession().delete(category);
    }

}
