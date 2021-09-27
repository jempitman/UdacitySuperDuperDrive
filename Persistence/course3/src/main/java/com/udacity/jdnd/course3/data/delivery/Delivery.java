package com.udacity.jdnd.course3.data.delivery;

import com.udacity.jdnd.course3.data.plant.Plant;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQuery(name = "Delivery.findByName",
        query = "select d from Delivery d where d.recipientName = :recipientName")
@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String recipientName;
    @Column(name = "address_full", length= 500)
    private String address;
    @Type(type = "yes_no")
    private Boolean completed = false;
    private LocalDateTime deliveryTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;

//    public Delivery(Long id, String recipientName, String address, Boolean completed,
//                    LocalDateTime deliveryTime) {
//        this.id = id;
//        this.recipientName = recipientName;
//        this.address = address;
//        this.completed = completed;
//        this.deliveryTime = deliveryTime;
//    }

    public Delivery(String recipientName, String address, LocalDateTime deliveryTime){
        this.recipientName = recipientName;
        this.address = address;
        this.deliveryTime = deliveryTime;
    }

    public Delivery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
