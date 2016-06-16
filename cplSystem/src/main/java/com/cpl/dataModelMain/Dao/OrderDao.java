package com.cpl.dataModelMain.Dao;

import com.cpl.dataModelMain.Domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hp on 2016/5/31.
 */
public interface OrderDao extends CrudRepository<Order, Integer>{
    public Order findById(int id);
    public Order save(Order order);
    public Iterable<Order> findAll();
}
