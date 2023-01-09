package gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public void save(User user) {
        User ateacher = entityManager.merge(user);
    }

    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

}
