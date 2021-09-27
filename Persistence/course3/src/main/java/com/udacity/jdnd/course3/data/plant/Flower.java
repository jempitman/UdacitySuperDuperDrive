package com.udacity.jdnd.course3.data.plant;

import javax.persistence.Entity;

@Entity
//@Table(name="flower")
public class Flower extends Plant{


    private String color;


//    public Flower(Long id, String name, BigDecimal price, Delivery delivery) {
//        super(id, name, price, delivery);
//    }

    public Flower() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
