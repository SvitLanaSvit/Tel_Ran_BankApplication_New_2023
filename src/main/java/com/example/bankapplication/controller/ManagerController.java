package com.example.bankapplication.controller;

import com.example.bankapplication.dto.CreateManagerDTO;
import com.example.bankapplication.dto.ManagerDTO;
import com.example.bankapplication.dto.ManagerListDTO;
import com.example.bankapplication.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    @PostMapping
    public ManagerDTO create(@RequestBody CreateManagerDTO manager){
        return managerService.create(manager);
    }

    @RequestMapping("managers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDTO getManagerById(@PathVariable UUID id){
        return managerService.getManagerById(id);
    }

    @RequestMapping("managers")
    @ResponseStatus(HttpStatus.OK)
    public ManagerListDTO getAllManagers(){
        return managerService.getManagersStatus();
    }

    @DeleteMapping("deleteManager/{id}")
    public void delete(@PathVariable UUID id){
        managerService.deleteById(id);
    }
}