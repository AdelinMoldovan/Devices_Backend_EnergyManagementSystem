package com.example.device_backend.services;

import com.example.device_backend.controllers.handlers.exceptions.ResourceNotFoundException;
import com.example.device_backend.models.Device;
import com.example.device_backend.models.User;
import com.example.device_backend.repositories.DeviceRepository;
import com.example.device_backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MappingService.class);
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    @Autowired
    public MappingService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    public void mapUserToDevice(Long energyMeterId, Long userId) {
        // Find the device by energyMeterId
        Device device = deviceRepository.findById(energyMeterId).orElse(null);

        if (device == null) {
            String errorMessage = "EnergyMeter with ID " + energyMeterId + " was not found in the database.";
            LOGGER.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }

        // Find the user by userId
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            String errorMessage = "User with ID " + userId + " was not found in the database.";
            LOGGER.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }

        device.setUser(user);

        deviceRepository.save(device);
    }

    public void updateMapping(Long energyMeterId, Long newUserId) {
        // Find the device by energyMeterId
        Device device = deviceRepository.findById(energyMeterId).orElse(null);

        if (device == null) {
            String errorMessage = "EnergyMeter with ID " + energyMeterId + " was not found in the database.";
            LOGGER.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }

        // Find the new user by newUserId
        User newUser = userRepository.findById(newUserId).orElse(null);

        if (newUser == null) {
            String errorMessage = "User with ID " + newUserId + " was not found in the database.";
            LOGGER.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }

        User currentUser = device.getUser();

        device.setUser(newUser);

        deviceRepository.save(device);
    }

    public void deleteMapping(Long energyMeterId) {
        // Find the device by energyMeterId
        Device device = deviceRepository.findById(energyMeterId).orElse(null);

        if (device == null) {
            String errorMessage = "EnergyMeter with ID " + energyMeterId + " was not found in the database.";
            LOGGER.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }

        User user = device.getUser();

        device.setUser(null);

        deviceRepository.save(device);
    }
}
