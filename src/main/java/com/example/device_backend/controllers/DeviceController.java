package com.example.device_backend.controllers;

import com.example.device_backend.dtos.DeviceDTO;
import com.example.device_backend.dtos.builder.DeviceBuilder;
import com.example.device_backend.models.Device;
import com.example.device_backend.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<Device>> getDevices() {
        List<Device> dtos = deviceService.findDevices();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> updateUser(@PathVariable Long id, @RequestBody DeviceDTO updatedDevice) {
        DeviceDTO updatedUserData = DeviceBuilder.toDeviceDTO(deviceService.update(id, updatedDevice));

        return new ResponseEntity<>(updatedUserData, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertDevice(@RequestBody DeviceDTO device) {
        Long deviceId = deviceService.insert(device);
        //System.out.println(device.getDescription() + device.getMaxHourlyEnergyConsumtion());
        return new ResponseEntity<>(deviceId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable("id") Long deviceId) {
        DeviceDTO dto = deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}



