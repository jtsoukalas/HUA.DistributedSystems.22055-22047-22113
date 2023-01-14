package gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTaxNumber(Integer taxNumber);

}