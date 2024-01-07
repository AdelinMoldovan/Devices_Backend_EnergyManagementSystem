package com.example.device_backend.dtos;

public class MappingDTO {
    private Long deviceId;
    private Long userId;

    public MappingDTO(Long deviceId, Long userId) {
        this.deviceId = deviceId;
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
