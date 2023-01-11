package gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao;


import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;

import java.util.List;

public interface DivorceDAO {
    public List<Divorce> findAll();
    public void save(Divorce divorce);
    public Divorce findById(int id);
}
