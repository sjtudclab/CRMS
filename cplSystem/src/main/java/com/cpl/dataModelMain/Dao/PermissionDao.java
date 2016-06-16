package com.cpl.dataModelMain.Dao;

import com.cpl.dataModelMain.Domain.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hp on 2016/5/31.
 */
public interface PermissionDao extends CrudRepository<Permission, Integer>{
    public Permission findById(int id);
    public Permission save(Permission permission);
    public Iterable<Permission> findAll();
}
