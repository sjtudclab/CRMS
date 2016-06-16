package com.cpl.dataModelMain.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by hp on 2016/5/31.
 */

@Entity
@Table(name = "repositoryrole")
public class RepositoryRole {
    @Id
    @NotNull
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "role")
    private int role;


    public RepositoryRole(){}
    public RepositoryRole(int _id, int _role){
        id = _id;
        role = _role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
