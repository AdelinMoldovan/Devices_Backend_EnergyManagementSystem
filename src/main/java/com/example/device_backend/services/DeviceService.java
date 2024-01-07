package com.example.device_backend.services;

import com.example.device_backend.controllers.handlers.exceptions.ResourceNotFoundException;
import com.example.device_backend.dtos.DeviceDTO;
import com.example.device_backend.dtos.builder.DeviceBuilder;
import com.example.device_backend.models.Device;
import com.example.device_backend.repositories.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> findDevices() {
        return deviceRepository.findAll();
    }

    public Device update(Long energyMeterId, DeviceDTO updatedDevice) {
        Device existingDevice = deviceRepository.findById(energyMeterId)
                .orElseThrow(() -> new ResourceNotFoundException("Energy Meter not found with id: " + energyMeterId));

        existingDevice.setName(updatedDevice.getName());
        existingDevice.setAddress(updatedDevice.getAddress());
        existingDevice.setDescription(updatedDevice.getDescription());
        existingDevice.setMaxHourlyEnergyConsumtion(updatedDevice.getMaxHourlyEnergyConsumtion());

        return deviceRepository.save(existingDevice);
    }

    public void delete(Long deviceId) {
        Device existingEm = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + deviceId));

        deviceRepository.delete(existingEm);

        LOGGER.debug("User with id {} was deleted from the database", deviceId);
    }

    public DeviceDTO findDeviceById(Long id) {
        Optional<Device> energyMeterOptional = deviceRepository.findById(id);
        if (energyMeterOptional.isEmpty()) {
            LOGGER.error("EnergyMeter with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toDeviceDTO(energyMeterOptional.get());
    }

    public Long insert(DeviceDTO deviceDTO) {
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("EnergyMeter with id {} was inserted in db", device.getDeviceId());
        return device.getDeviceId();
    }
}
