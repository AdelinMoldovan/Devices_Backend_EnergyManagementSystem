package com.example.device_backend.dtos.builder;

import com.example.device_backend.dtos.DeviceDTO;
import com.example.device_backend.models.Device;

public class DeviceBuilder {

    private DeviceBuilder() {
    }

    public static DeviceDTO toDeviceDTO(Device device) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setName(device.getName());
        deviceDTO.setAddress(device.getAddress());
        deviceDTO.setDescription(device.getDescription());
        deviceDTO.setMaxHourlyEnergyConsumtion(device.getMaxHourlyEnergyConsumtion());
        if (device.getUser() != null) {
            deviceDTO.setUser(device.getUser());
        }

        return deviceDTO;
    }

    public static Device toEntity(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setName(deviceDTO.getName());
        device.setAddress(deviceDTO.getAddress());
        device.setDescription(deviceDTO.getDescription());
        device.setMaxHourlyEnergyConsumtion(deviceDTO.getMaxHourlyEnergyConsumtion());

        if (deviceDTO.getUser() != null) {
            device.setUser(deviceDTO.getUser());
        }

        return device;
    }
}

