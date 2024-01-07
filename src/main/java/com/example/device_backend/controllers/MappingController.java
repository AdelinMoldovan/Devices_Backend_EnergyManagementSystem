package com.example.device_backend.controllers;

import com.example.device_backend.controllers.handlers.exceptions.ResourceNotFoundException;
import com.example.device_backend.dtos.MappingDTO;
import com.example.device_backend.services.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/mappings")
public class MappingController {

    @Autowired
    private MappingService mappingService;

    @PostMapping("/map")
    public ResponseEntity<Void> map(@RequestBody MappingDTO mappingDTO) {
        try {
            mappingService.mapUserToDevice(mappingDTO.getDeviceId(), mappingDTO.getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getStatus());
        }
    }

    @DeleteMapping("/unmap")
    public ResponseEntity<Void> unmap(@RequestBody MappingDTO mappingDTO) {
        try {
            mappingService.deleteMapping(mappingDTO.getDeviceId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getStatus());
        }
    }
}
