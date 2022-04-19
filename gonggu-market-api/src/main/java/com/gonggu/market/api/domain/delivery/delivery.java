package com.gonggu.market.api.domain.delivery;

import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.order.Order;

import javax.persistence.*;

@Entity
public class delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public delivery() {
    }

    public delivery(Long id, Order order, Address address) {
        this.id = id;
        this.order = order;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
