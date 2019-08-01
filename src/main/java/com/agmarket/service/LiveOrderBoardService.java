package com.agmarket.service;

import com.agmarket.domain.Order;
import com.agmarket.domain.OrderType;

import java.util.List;

public interface LiveOrderBoardService {

    Order registerOrder(Order order);

    void removeOrder(Long orderId);

    List<String>  getOrders(OrderType orderType);
}
