package com.cpl.service;

import com.cpl.dataModelMain.Dao.RepositoryRoleDao;
import com.cpl.dataModelMain.Domain.RepositoryRole;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hp on 2016/5/31.
 */
@Service
public class RepositoryRoleService {
    @Autowired
    RepositoryRoleDao roleDao;

    public Boolean isFindId(int id){
        RepositoryRole role = roleDao.findById(id);
        return role != null;
    }

    public void updateRole(int id, int role){
        RepositoryRole repositoryRole = new RepositoryRole(id, role);
        roleDao.save(repositoryRole);
    }

    public Iterable<RepositoryRole> getAll(){
        return roleDao.findAll();
    }

    public int getRole(int id) {
        RepositoryRole role = roleDao.findById(id);
        if (role != null)
            return role.getRole();
        else
            return -1;
    }
}
