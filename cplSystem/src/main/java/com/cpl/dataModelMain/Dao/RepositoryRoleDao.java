package com.cpl.dataModelMain.Dao;

import com.cpl.dataModelMain.Domain.RepositoryRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hp on 2016/5/31.
 */
public interface RepositoryRoleDao extends CrudRepository<RepositoryRole, Integer>{
//    @Query("select * from user order by authority")
//    public List<User> findAllUser();

    public RepositoryRole findById(int id);
    public Iterable<RepositoryRole> findAll();
    public RepositoryRole save(RepositoryRole user);

}
