package com.cpl.dataModelMain.Dao;

import com.cpl.dataModelMain.Domain.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hp on 2016/5/31.
 */
public interface ProjectDao extends CrudRepository<Project, Integer>{
//    @Query("select * from user order by authority")
//    public List<User> findAllUser();

    public Project findById(int id);
    public Project save(Project project);

}
