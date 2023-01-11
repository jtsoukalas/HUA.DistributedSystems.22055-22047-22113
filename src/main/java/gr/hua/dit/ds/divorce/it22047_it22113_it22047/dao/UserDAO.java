package gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao;


import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import java.util.List;

public interface UserDAO {
    public List<User> findAll();
    public void save(User user);
    public User findById(String id);
    public void delete(Integer id);
}
