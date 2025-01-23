package com.company.project.controllers;

import com.company.project.dto.AccidentDTO;
import com.company.project.services.AccidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accidents")
public class AccidentController {

    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @PostMapping
    public AccidentDTO createAccident(@RequestBody AccidentDTO accidentDTO) {
        return accidentService.saveAccident(accidentDTO);
    }

    @GetMapping("/{id}")
    public AccidentDTO getAccidentById(@PathVariable Long id) {
        return accidentService.getAccidentById(id);
    }

    @GetMapping
    public List<AccidentDTO> getAllAccidents() {
        return accidentService.getAllAccidents();
    }

    @DeleteMapping("/{id}")
    public void deleteAccident(@PathVariable Long id) {
        accidentService.deleteAccidentById(id);
    }
}