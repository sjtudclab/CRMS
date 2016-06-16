package com.cpl.Controller;

import com.cpl.dataModelMain.Domain.Permission;
import com.cpl.httpSender.HttpSender;
import com.cpl.service.PermissionService;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/permission/get", method = RequestMethod.GET)
    public @ResponseBody
    Boolean getOne(int id, int index){
        System.out.println(id + ' ' + index);
        return permissionService.getPermissionValue(id, index);
    }

    @RequestMapping(value = "/permission/update", method = RequestMethod.GET)
    public @ResponseBody
    Boolean update(int id, String name, int all, int pone, int ptwo, int pthree, int pfour, int pfive,
                   int pcreate_team, int pcreate_pro, int padd_per, int pdel_per, int ppro_merge, int ppro_in){
        Permission p = new Permission(id, name, all, pone, ptwo, pthree, pfour, pfive, pcreate_team, pcreate_pro,
                                    padd_per, pdel_per, ppro_merge, ppro_in);
        permissionService.updatePermission(p);
        return true;
    }

    @RequestMapping(value = "/permission/list", method = RequestMethod.GET)
    public @ResponseBody
    String list(){
        Iterable<Permission> pList = permissionService.getAll();
        Iterator iter = pList.iterator();
        JSONArray result = new JSONArray();
        while(iter.hasNext()){
            Permission p = (Permission)iter.next();
            JSONObject obj = new JSONObject();
            obj.put("name", p.getName());
            result.put(obj);
        }
       // System.out.println(result);
        return result.toString();
    }

    @RequestMapping(value = "/permission/detail", method = RequestMethod.GET)
    public @ResponseBody
    String detail(int id){
        Permission p = permissionService.findById(id);
        JSONArray result = new JSONArray();
        JSONObject obj = setMsg(p);
        result.put(obj);
        return result.toString();
    }

    @RequestMapping(value = "/permission/all", method = RequestMethod.GET)
    public @ResponseBody
    String all(){
        Iterable<Permission> pList = permissionService.getAll();
        Iterator iter = pList.iterator();
        JSONArray result = new JSONArray();
        while(iter.hasNext()){
            Permission p = (Permission)iter.next();
            JSONObject obj = setMsg(p);
            result.put(obj);
        }
        return result.toString();
    }

    private JSONObject setMsg(Permission p){
        JSONObject obj = new JSONObject();
        obj.put("name", p.getName());
        obj.put("pone", p.getPone());
        obj.put("ptwo", p.getPtwo());
        obj.put("pthree", p.getPthree());
        obj.put("pfour", p.getPfour());
        obj.put("pfive", p.getPfive());
        obj.put("pcreate_team", p.getPcreate_team());
        obj.put("pcreate_pro", p.getPcreate_pro());
        obj.put("padd_per", p.getPadd_per());
        obj.put("pdel_per", p.getPdel_per());
        obj.put("ppro_in", p.getPpro_in());
        obj.put("ppro_merge", p.getPpro_merge());
        return obj;
    }
}

