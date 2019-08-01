package com.agmarket.service;

import com.agmarket.domain.Order;
import com.agmarket.domain.OrderType;
import com.agmarket.domain.Quantity;
import com.agmarket.repository.OrderRepository;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.agmarket.domain.OrderType.BUY;
import static com.agmarket.domain.OrderType.SELL;
import static com.agmarket.domain.Unit.KILO_GRAM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.GBP;

public class LiveOrderBoardServiceImplTest {
    private LiveOrderBoardService liveOrderBoardService;


    @Before
    public void setUp() {
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.removeAll();
        liveOrderBoardService = new LiveOrderBoardServiceImpl(orderRepository);
    }

    @Test
    public void shouldListSellOrdersInAscendingOrder() {
        getOrders(SELL).forEach(order -> liveOrderBoardService.registerOrder(order));
        assertThat(liveOrderBoardService.getOrders(SELL)).containsExactly("5.5 kg for £306.00", "1.5 kg for £307.00", "1.2 kg for £310.00");
        liveOrderBoardService.removeOrder(1l);
        assertThat(liveOrderBoardService.getOrders(SELL)).containsExactly("2.0 kg for £306.00", "1.5 kg for £307.00", "1.2 kg for £310.00");
    }

    @Test
    public void shouldListBuyOrdersInAscendingOrder() {
        getOrders(BUY).forEach(order -> liveOrderBoardService.registerOrder(order));
        assertThat(liveOrderBoardService.getOrders(BUY)).containsExactly("1.2 kg for £310.00", "1.5 kg for £307.00", "5.5 kg for £306.00");
        liveOrderBoardService.removeOrder(2l);
        assertThat(liveOrderBoardService.getOrders(BUY)).containsExactly("1.5 kg for £307.00", "5.5 kg for £306.00");
    }

    private List<Order> getOrders(OrderType orderType) {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(1L, "user1", new Quantity(3.5d, KILO_GRAM), Money.of(GBP, 306d), orderType));
        orders.add(new Order(2L, "user2", new Quantity(1.2d, KILO_GRAM), Money.of(GBP, 310d), orderType));
        orders.add(new Order(3L, "user3", new Quantity(1.5d, KILO_GRAM), Money.of(GBP, 307d), orderType));
        orders.add(new Order(4L, "user4", new Quantity(2.0d, KILO_GRAM), Money.of(GBP, 306d), orderType));

        return orders;
    }


}