package com.example.demo.controller;

import com.example.demo.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("damagedSystem")
public class ApiController {

    @GetMapping("/repair-bay")
    public String getRepairBay(Model model) {
        if(Service.getDamagedSystem().isEmpty()) {
            return "redirect:/update";
        }
        String systemCode = Service.getSystemCodes().get(Service.getDamagedSystem());
        model.addAttribute("systemCode", systemCode);
        return "index";
    }

    @GetMapping("/update")
    public String updateSystem () {
        return "update";
    }

    @PostMapping("/update-system")
    public String updateSystem(@RequestParam String system) {
        if (Service.getSystemCodes().containsKey(system)) {
            Service.setDamagedSystem(system);
        }
        return "redirect:/repair-bay";
    }
}
