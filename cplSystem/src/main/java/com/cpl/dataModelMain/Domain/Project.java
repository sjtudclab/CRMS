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
@Table(name = "project")
public class Project {
    @Id
    @NotNull
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "type")
    private int type;

    @NotNull
    @Column(name = "state")
    private int state;

    public Project(){}
    public Project(int _id, int _type, int _state){
        id = _id;
        type = _type;
        state = _state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
