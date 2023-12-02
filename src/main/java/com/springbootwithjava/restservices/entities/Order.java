package com.springbootwithjava.restservices.entities;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

@Entity
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue
    @JsonView(Views.Internal.class)
    private Long orderid;
    @JsonView(Views.Internal.class)
    private String orderdescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Order(){
        super();
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
