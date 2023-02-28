package gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatement;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.beans.Statement;
import java.util.List;
import java.util.Optional;

public interface DivorceStatementRepository extends JpaRepository<DivorceStatement, Long> {
    Optional<DivorceStatement> findById(Integer taxNumber);
    @Transactional
    Optional<DivorceStatement> deleteById(Integer id);
    void deleteAllByDivorceId(Integer id);
}
