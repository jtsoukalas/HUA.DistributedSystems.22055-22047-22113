package gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class DivorceDAOImp implements DivorceDAO {

    //fixme
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Divorce> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Divorce", Divorce.class);
        List<Divorce> divorces = query.getResultList();
        return divorces;
    }

    @Override
    @Transactional
    public void save(Divorce divorce) {
        Divorce adivorce = entityManager.merge(divorce);
    }

    @Override
    @Transactional
    public Divorce findById(int id) {
        return entityManager.find(Divorce.class, id);
    }
}



