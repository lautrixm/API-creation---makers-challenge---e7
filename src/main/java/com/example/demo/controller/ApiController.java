package com.example.demo.controller;

import com.example.demo.services.Service;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("damagedSystem")
public class ApiController {

    @Operation
    @GetMapping("/repair-bay")
    public String getRepairBay(Model model) {
        if(Service.getDamagedSystem().isEmpty()) {
            Service.setDamagedSystem("navigation");
        }
        String systemCode = Service.getSystemCodes().get(Service.getDamagedSystem());
        model.addAttribute("systemCode", systemCode);
        return "index";
    }

    @Hidden
    @GetMapping("/update")
    public String updateSystem () {
        return "update";
    }

    @Operation
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/update-system")
    public String updateSystem(@RequestParam String system) {
        if (Service.getSystemCodes().containsKey(system)) {
            Service.setDamagedSystem(system);
        }
        return "redirect:/repair-bay";
    }
}