package com.cpl.service;

import com.cpl.dataModelMain.Dao.ProjectDao;
import com.cpl.dataModelMain.Domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hp on 2016/5/31.
 */
@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;

    public Boolean isFindId(int id){
        Project role = projectDao.findById(id);
        return role != null;
    }

    public void modifyType(int id, int type, int state){
        Project project = new Project(id, type, state);
        projectDao.save(project);
    }

    public int getType(int id) {
        Project project = projectDao.findById(id);
        if (project != null)
            return project.getType();
        else
            return -1;
    }

    public int getState(int id){
        Project project = projectDao.findById(id);
        if (project != null)
            return project.getState();
        else
            return -1;
    }
}
