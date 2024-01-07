package com.example.device_backend.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Entity
@Table(name = "devices")
public class Device implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
    private String name;
    private String address;
    private String description;
    private String maxHourlyEnergyConsumtion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setDeviceId(Long energyMeterId) {
        this.deviceId = energyMeterId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxHourlyEnergyConsumtion(String maxHourlyEnergyConsumtion) {
        this.maxHourlyEnergyConsumtion = maxHourlyEnergyConsumtion;
    }
}
