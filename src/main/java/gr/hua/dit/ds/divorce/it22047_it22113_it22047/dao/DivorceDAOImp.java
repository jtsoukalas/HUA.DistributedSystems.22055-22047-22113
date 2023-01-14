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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    @Transactional
    public List<Divorce> findByTaxNumber(String taxNumber) {
        Session session = entityManager.unwrap(Session.class);
//        Find all the statements with this tax number
//        Query query = session.createQuery("from DivorceStatement where person_id = :taxNumber", DivorceStatement.class);
//        query.setParameter("taxNumber", taxNumber);

        List<DivorceStatement> statements = divorceStatementRepo.findById(taxNumber).orElse(null);
//        statements.forEach(()->{
//            Logger.getLogger().error(statements.toString());
//        });
        if (statements.isEmpty()) {
            throw new NoSuchElementException("No statements found for this tax number");
        }

        Logger LOGGER= (Logger) LoggerFactory.getLogger(DivorceApplication.class);



            LOGGER.info("S with inputs {}, {} and {}");

//        save queryStatements in a list
//        List<DivorceStatement> statements = queryStatements.getResultList();
//        take all the divorce ids from the statements and save them in a list
        List<String> divorceIds = statements.stream().map(DivorceStatement::getId).toList();
        divorceIds.stream().distinct();
        List<Divorce> divorces = divorceIds.stream().map(id -> findById(id)).toList();

        return divorces;
    }
}



