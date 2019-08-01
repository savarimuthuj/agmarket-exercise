package com.agmarket.domain;

import org.joda.money.Money;
import org.junit.Test;

import static com.agmarket.domain.OrderType.BUY;
import static com.agmarket.domain.OrderType.SELL;
import static com.agmarket.domain.Unit.KILO_GRAM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.joda.money.CurrencyUnit.GBP;


public class OrderTest {


    @Test
    public void shouldMatchAllFields() {
        Order order = new Order(1L,"user1", new Quantity(3.5d, KILO_GRAM), Money.of(GBP, 303d), SELL);
        assertThat(order.getOrderId()).isEqualTo(1);
        assertThat(order.getUserId()).isEqualTo("user1");
        assertThat(order.getQuantity().getAmount()).isEqualTo(3.5d);
        assertThat(order.getQuantity().getUnit().toString()).isEqualTo("kg");
        assertThat(order.getOrderType()).isEqualTo(SELL);
        order.setOrderId(2L);
        order.setUserId("user2");
        Quantity quantity = new Quantity();
        quantity.setAmount(0.01d);
        quantity.setUnit(KILO_GRAM);
        order.setQuantity(quantity);
        order.setPrice(Money.of(Money.of(GBP, 3d)));
        order.setOrderType(BUY);
        assertThat(order.getOrderId()).isEqualTo(2);
        assertThat(order.getUserId()).isEqualTo("user2");
        assertThat(order.getQuantity().getAmount()).isEqualTo(0.01d);
        assertThat(order.getQuantity().getUnit().toString()).isEqualTo("kg");
        assertThat(order.getOrderType()).isEqualTo(BUY);

    }
    @Test
    public void shouldMatchEqualsBasedOnOrderId() {
        Order order1 = new Order();
        order1.setOrderId(1l);
        Order order2 = new Order();
        order2.setOrderId(1l);
        assertThat(order1).isEqualTo(order2);
        Order order3 = new Order();
        order2.setOrderId(2l);
        assertThat(order1).isNotEqualTo(order3);
    }
}
