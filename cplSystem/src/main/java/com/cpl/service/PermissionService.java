package com.cpl.service;

import com.cpl.dataModelMain.Dao.PermissionDao;
import com.cpl.dataModelMain.Domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hp on 2016/5/31.
 */
@Service
public class PermissionService {
    @Autowired
    PermissionDao permissionDao;

    public Boolean isFind(int id){
        Permission permission = permissionDao.findById(id);
        return permission != null;
    }

    public void updatePermission(Permission _p){
        Permission p = new Permission(_p);
        permissionDao.save(p);
    }

    public Permission findById(int id){
        return permissionDao.findById(id);
    }

    public boolean getPermissionValue(int id, int index) {
        Permission permission = permissionDao.findById(id);
        //System.out.println("getValue:" + id + ' ' + index + ' ' + permission.getPcreate_team());
        return permission != null ? permission.getPermission(index) : false;
    }

    public Iterable<Permission> getAll(){
        return permissionDao.findAll();
    }
}
