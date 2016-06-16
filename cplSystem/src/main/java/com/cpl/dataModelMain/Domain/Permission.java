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
@Table(name = "permission")
public class Permission {
    @Id
    @NotNull
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "pall")
    private int all;

    @NotNull
    @Column(name = "pone")
    private int pone;

    @NotNull
    @Column(name = "ptwo")
    private int ptwo;

    @NotNull
    @Column(name = "pthree")
    private int pthree;

    @NotNull
    @Column(name = "pfour")
    private int pfour;

    @NotNull
    @Column(name = "pfive")
    private int pfive;

    @NotNull
    @Column(name = "pcreate_team")
    private int pcreate_team;

    @NotNull
    @Column(name = "pcreate_pro")
    private int pcreate_pro;

    @NotNull
    @Column(name = "padd_per")
    private int padd_per;

    @NotNull
    @Column(name = "pdel_per")
    private int pdel_per;

    @NotNull
    @Column(name = "ppro_merge")
    private int ppro_merge;

    @NotNull
    @Column(name = "ppro_in")
    private int ppro_in;

    public Permission(){}
    public Permission(Permission p){
        id = p.id;
        name = p.name;
        all = p.all;
        pone = p.pone;
        ptwo = p.ptwo;
        pthree = p.pthree;
        pfour = p.pfour;
        pfive = p.pfive;
        pcreate_team = p.pcreate_team;
        pcreate_pro = p.pcreate_pro;
        padd_per = p.padd_per;
        pdel_per = p.pdel_per;
        ppro_merge = p.ppro_merge;
        ppro_in = p.ppro_in;
    }
    public Permission(int _id, String _name, int _all, int _one, int _two,
                      int _three, int _four, int _five, int _create_team,
                      int _create_pro, int _add_per, int _del_per,
                      int _pro_merge, int _pro_in){
        id = _id;
        name = _name;
        all = _all;
        pone = _one;
        ptwo = _two;
        pthree = _three;
        pfour = _four;
        pfive = _five;
        pcreate_team = _create_team;
        pcreate_pro = _create_pro;
        padd_per = _add_per;
        pdel_per = _del_per;
        ppro_merge = _pro_merge;
        ppro_in = _pro_in;
    }

    public boolean getPermission(int index){
        switch (index){
            case 0:
                return all == 1 ? true : false;
            case 1:
                return pone == 1 ? true : false;
            case 2:
                return ptwo == 1 ? true : false;
            case 3:
                return pthree == 1 ? true : false;
            case 4:
                return pfour == 1 ? true : false;
            case 5:
                return pfive == 1 ? true : false;
            case 6:
                return pcreate_team == 1 ? true : false;
            case 7:
                return pcreate_pro == 1 ? true : false;
            case 8:
                return padd_per == 1 ? true : false;
            case 9:
                return pdel_per == 1 ? true : false;
            case 10:
                return ppro_merge == 1 ? true : false;
            case 11:
                return ppro_in == 1 ? true : false;
            default:
                break;
        }
        return false;
    }

    public int getPpro_in() {
        return ppro_in;
    }

    public void setPpro_in(int ppro_in) {
        this.ppro_in = ppro_in;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPone() {
        return pone;
    }

    public void setPone(int pone) {
        this.pone = pone;
    }

    public int getPtwo() {
        return ptwo;
    }

    public void setPtwo(int ptwo) {
        this.ptwo = ptwo;
    }

    public int getPthree() {
        return pthree;
    }

    public void setPthree(int pthree) {
        this.pthree = pthree;
    }

    public int getPfour() {
        return pfour;
    }

    public void setPfour(int pfour) {
        this.pfour = pfour;
    }

    public int getPfive() {
        return pfive;
    }

    public void setPfive(int pfive) {
        this.pfive = pfive;
    }

    public int getPcreate_team() {
        return pcreate_team;
    }

    public void setPcreate_team(int pcreate_team) {
        this.pcreate_team = pcreate_team;
    }

    public int getPcreate_pro() {
        return pcreate_pro;
    }

    public void setPcreate_pro(int pcreate_pro) {
        this.pcreate_pro = pcreate_pro;
    }

    public int getPadd_per() {
        return padd_per;
    }

    public void setPadd_per(int padd_per) {
        this.padd_per = padd_per;
    }

    public int getPdel_per() {
        return pdel_per;
    }

    public void setPdel_per(int pdel_per) {
        this.pdel_per = pdel_per;
    }

    public int getPpro_merge() {
        return ppro_merge;
    }

    public void setPpro_merge(int ppro_merge) {
        this.ppro_merge = ppro_merge;
    }
}
