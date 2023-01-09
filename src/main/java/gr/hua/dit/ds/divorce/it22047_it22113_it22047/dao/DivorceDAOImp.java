package gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class DivorceDAOImp implements DivorceDAO {

    //fixme
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Divorce> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Divorce", Divorce.class);
        List<Divorce> divorces = query.getResultList();
        return divorces;
    }

    @Override
    public void save(Divorce divorce) {
        Divorce acourse = entityManager.merge(divorce);
    }

    @Override
    public Divorce findById(String id) {
        return entityManager.find(Divorce.class, id);
    }
}



