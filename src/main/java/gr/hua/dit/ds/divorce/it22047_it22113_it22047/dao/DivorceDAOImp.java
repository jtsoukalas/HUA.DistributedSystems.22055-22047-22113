package gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.DivorceApplication;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatement;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatus;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceStatementRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlType;
import java.beans.Statement;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;


@Repository
public class DivorceDAOImp implements DivorceDAO {

    //fixme
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DivorceStatementRepository divorceStatementRepo;
    @Autowired
    private UserRepository userRepo;

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
    public Divorce findById(String id) {
        return entityManager.find(Divorce.class, id);
    }


}



