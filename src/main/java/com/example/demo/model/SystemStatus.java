package com.example.demo.model;

public class SystemStatus {
    private String damagedSystem;

    public SystemStatus() {}

    public SystemStatus(String damagedSystem) {
        this.damagedSystem = damagedSystem;
    }

    public String getDamagedSystem() {
        return damagedSystem;
    }

    public void setDamagedSystem(String damagedSystem) {
        this.damagedSystem = damagedSystem;
    }
}
