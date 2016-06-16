package com.cpl.Controller;

import com.cpl.dataModelMain.Domain.RepositoryRole;
import com.cpl.httpSender.HttpSender;
import com.cpl.service.OrderService;
import com.cpl.service.ProjectService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProjectService projectService;

    private HttpSender sender = new HttpSender();
    /* state index:
            0 : just send
            1 : pass
            2 : not pass
     */

    //get all order
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public @ResponseBody
    String getAll(){
        return orderService.findAll().toString();
    }

    //get order by uid
    @RequestMapping(value = "/order/uid", method = RequestMethod.GET)
    public @ResponseBody
    String getByUid(int uid){
        return orderService.findByUid(uid).toString();
    }

    //get order by state
    @RequestMapping(value = "/order/state", method = RequestMethod.GET)
    public @ResponseBody
    String getByState(int state){
        return orderService.findByState(state).toString();
    }

    //save
    @RequestMapping(value = "/order/save", method = RequestMethod.GET)
    public @ResponseBody
    Boolean save(int uid, int pid, String description){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = format.format(date);
        orderService.save(uid, pid, 0, description, data);
        return true;
    }

    @RequestMapping(value = "/order/enter", method = RequestMethod.GET)
    public @ResponseBody
    Boolean enter(int id, int type, int oid, int state){
        //HttpSender sender = new HttpSender();
        projectService.modifyType(id, type, 1);
        orderService.modifyState(oid, state);
        return true;
    }

}


