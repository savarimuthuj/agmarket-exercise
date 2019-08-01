package com.agmarket.service;

import com.agmarket.domain.Order;
import com.agmarket.domain.OrderType;
import com.agmarket.repository.Repository;
import org.joda.money.Money;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.agmarket.domain.OrderType.BUY;

public class LiveOrderBoardServiceImpl implements LiveOrderBoardService {
    private final Repository<Order> orderRepository;

    public LiveOrderBoardServiceImpl(Repository<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<String> getOrders(OrderType orderType) {
        List<Order> orders = this.orderRepository.getAll();

        List<Order> filteredOrder = orders.stream()
                .filter(order -> order.getOrderType().equals(orderType))
                .sorted(Comparator.comparing(Order::getPrice))
                .collect(Collectors.toList());
        Map<Money, List<Order>> ordersGroupByPrice = filteredOrder.stream().collect(Collectors.groupingBy(Order::getPrice));

        List<String> displayTextOrderByPrice = ordersGroupByPrice.keySet()
                .stream()
                .sorted(Money::compareTo)
                .map(key -> "" + ordersGroupByPrice.get(key).stream().collect(Collectors.summarizingDouble(order ->
                        order.getQuantity().getAmount())).getSum() +
                        " " + ordersGroupByPrice.get(key).get(0).getQuantity().getUnit().toString() +
                        " for " + ordersGroupByPrice.get(key).get(0).getPrice().getCurrencyUnit().getSymbol() +
                        ordersGroupByPrice.get(key).get(0).getPrice().getAmount().toPlainString())
                .collect(Collectors.toList());

        if (orderType.equals(BUY)) {
            Collections.reverse(displayTextOrderByPrice);
        }
        return displayTextOrderByPrice;
    }

    @Override
    public void removeOrder(Long orderId) {
        this.orderRepository.removeById(orderId);
    }

    @Override
    public Order registerOrder(Order order) {
        return this.orderRepository.save(order);
    }
}
