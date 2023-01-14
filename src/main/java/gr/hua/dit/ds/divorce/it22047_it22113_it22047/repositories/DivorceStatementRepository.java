package gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatement;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.Statement;
import java.util.List;
import java.util.Optional;

public interface DivorceStatementRepository extends JpaRepository<DivorceStatement, Long> {
    Optional<List<DivorceStatement>> findById(Integer taxNumber);
}
