package com.cpl.service;

import com.cpl.dataModelMain.Dao.OrderDao;
import com.cpl.dataModelMain.Domain.Order;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by hp on 2016/5/31.
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public JSONArray findAll(){
        Iterable<Order> orderList = orderDao.findAll();
        JSONArray result = new JSONArray();
        Iterator iter = orderList.iterator();
        while (iter.hasNext()) {
            Order order = (Order) iter.next();
            JSONObject obj = setMsg(order);
            result.put(obj);
        }
        return result;
    }

    public JSONArray findByUid(int uid){
        Iterable<Order> orderList = orderDao.findAll();
        JSONArray result = new JSONArray();
        Iterator iter = orderList.iterator();
        while (iter.hasNext()) {
            Order order = (Order) iter.next();
            if(order.getUid() == uid){
                JSONObject obj = setMsg(order);
                result.put(obj);
            }

        }

        return result;
    }

    public JSONArray findByState(int state){
        Iterable<Order> orderList = orderDao.findAll();
        JSONArray result = new JSONArray();
        Iterator iter = orderList.iterator();
        while (iter.hasNext()) {
            Order order = (Order) iter.next();
            if(order.getState() == state){
                JSONObject obj = setMsg(order);
                result.put(obj);
            }

        }

        return result;
    }

    public void save(int uid, int pid, int type, String description, String date){
        Order o = new Order(uid, pid, 0, type, description, date);
        orderDao.save(o);
    }

    public void modifyState(int id, int state){
        Order o = orderDao.findById(id);
        o.setState(state);
        orderDao.save(o);
    }

    private JSONObject setMsg(Order o){
        JSONObject obj = new JSONObject();
        obj.put("id", o.getId());
        obj.put("pid", o.getPid());
        obj.put("uid", o.getUid());
        obj.put("state", o.getState());
        obj.put("description", o.getDescription());
        obj.put("type", o.getType());
        obj.put("date", o.getDate());
        return obj;
    }
}
