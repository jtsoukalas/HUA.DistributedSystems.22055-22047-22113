package gr.hua.dit.ds.divorce.it22047_it22113_it22047.service;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserDAO userDAO;
}
