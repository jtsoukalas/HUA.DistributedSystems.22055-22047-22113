package gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    @Transactional
    public void save(User user) {
        User ateacher = entityManager.merge(user);
    }

    @Override
    @Transactional
    public User findById(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

}
