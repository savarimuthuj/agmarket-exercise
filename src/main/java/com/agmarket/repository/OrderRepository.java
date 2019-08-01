package com.agmarket.repository;

import com.agmarket.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository implements Repository<Order> {
    private static Map<Long, Order> orders = SingleOrders.INSTANCE.getOrders();

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order get(Long id) {
        return orders.get(id);
    }

    @Override
    public Order save(Order order) {
        return orders.put(order.getOrderId(), order);
    }

    @Override
    public boolean remove(Order order) {
        return removeById(order.getOrderId());
    }

    @Override
    public boolean removeById(Long id) {
        return orders.remove(id) != null;
    }

    @Override
    public void removeAll() {
        orders.clear();
    }

    @Override
    public int count() {
        return orders.size();
    }

    private enum SingleOrders {
        INSTANCE;
        Map<Long, Order> orders = new ConcurrentHashMap<>();

        public Map<Long, Order> getOrders() {
            return orders;
        }
    }
}
