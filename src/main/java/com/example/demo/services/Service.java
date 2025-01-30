package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

public class Service {
    private static final Map<String, String> SYSTEM_CODES = new HashMap<>();
    private static String damagedSystem = ""; // Variable compartida

    static {
        SYSTEM_CODES.put("navigation", "NAV-01");
        SYSTEM_CODES.put("communications", "COM-02");
        SYSTEM_CODES.put("life_support", "LIFE-03");
        SYSTEM_CODES.put("engines", "ENG-04");
        SYSTEM_CODES.put("deflector_shield", "SHLD-05");
    }

    public static Map<String, String> getSystemCodes() {
        return SYSTEM_CODES;
    }

    public static String getSystemCode(String system) {
        return SYSTEM_CODES.get(system);
    }

    public static void setSystemCode(String system, String code) {
        SYSTEM_CODES.put(system, code);
    }

    public static String getDamagedSystem() {
        return damagedSystem;
    }

    public static void setDamagedSystem(String system) {
        damagedSystem = system;
    }
}
