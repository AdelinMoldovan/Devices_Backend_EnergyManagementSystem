package com.example.device_backend.dtos;

import com.example.device_backend.models.User;

public class DeviceDTO {
    private String name;
    private String address;
    private String description;
    private String maxHourlyEnergyConsumtion;
    private User user;

    public DeviceDTO(String name, String address,String description,String maxHourlyEnergyConsumtion, User user) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.maxHourlyEnergyConsumtion=maxHourlyEnergyConsumtion;
        this.user = user;
    }
    public DeviceDTO() {

    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxHourlyEnergyConsumtion() {
        return maxHourlyEnergyConsumtion;
    }

    public void setMaxHourlyEnergyConsumtion(String maxHourlyEnergyConsumtion) {
        this.maxHourlyEnergyConsumtion = maxHourlyEnergyConsumtion;
    }
}
