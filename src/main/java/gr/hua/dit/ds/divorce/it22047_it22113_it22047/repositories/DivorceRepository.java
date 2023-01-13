package gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories;


import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivorceRepository extends JpaRepository<Divorce, Long> {
}
