package com.agmarket.repository;

import com.agmarket.domain.Order;
import com.agmarket.domain.Quantity;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import static com.agmarket.domain.OrderType.BUY;
import static com.agmarket.domain.OrderType.SELL;
import static com.agmarket.domain.Unit.KILO_GRAM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.joda.money.CurrencyUnit.GBP;

public class OrderRepositoryTest {
    OrderRepository orderRepository;

    @Before
    public void setUp(){
        orderRepository = new OrderRepository();
        orderRepository.removeAll();
    }

    @Test
    public void shouldSave(){
        Order order1 = new Order(1L,"user1", new Quantity(3.5d, KILO_GRAM), Money.of(GBP, 303d), SELL);
        orderRepository.save(order1);
        Order order2 = new Order(2L,"user2", new Quantity(3.0d, KILO_GRAM), Money.of(GBP, 303d), SELL);
        orderRepository.save(order2);
        Order order3 = new Order(1L,"user2", new Quantity(3.0d, KILO_GRAM), Money.of(GBP, 303d), BUY);
        orderRepository.save(order3);
        Order order4 = new Order(4L,"user4", new Quantity(3.0d, KILO_GRAM), Money.of(GBP, 303d), SELL);
        orderRepository.save(order4);
        assertThat(orderRepository.remove(order4)).isTrue();
        Order order5 = new Order(5L,"user5", new Quantity(3.0d, KILO_GRAM), Money.of(GBP, 303d), SELL);
        orderRepository.save(order5);
        assertThat(orderRepository.removeById(5L)).isTrue();
        assertThat(orderRepository.get(2L).getOrderId()).isEqualTo(2L);
        assertThat(orderRepository.getAll().size()).isEqualTo(2);
        assertThat(orderRepository.count()).isEqualTo(2);
    }
}
