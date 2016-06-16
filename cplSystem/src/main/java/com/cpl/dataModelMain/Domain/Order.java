package com.cpl.dataModelMain.Domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by hp on 2016/5/31.
 */

@Entity
@Table(name = "repo_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @GenericGenerator(name = "increment", strategy = "increment")
    @NotNull
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "pid")
    private int pid;

    @NotNull
    @Column(name = "uid")
    private int uid;

    @NotNull
    @Column(name = "state")
    private int state;

    @NotNull
    @Column(name = "type")
    private int type;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "date_time")
    private String date;

    public Order(){}
    public Order(int _pid, int _uid, int _state, int _type, String _description, String _date){
        pid = _pid;
        uid = _uid;
        type = _type;
        state = _state;
        description = _description;
        date = _date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
