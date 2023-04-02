package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.CreateManagerDTO;
import com.example.bankapplication.dto.ManagerDTO;
import com.example.bankapplication.dto.ManagerListDTO;
import com.example.bankapplication.entity.enums.ManagerStatus;
import com.example.bankapplication.mapper.ManagerMapper;
import com.example.bankapplication.repository.ManagerRepository;
import com.example.bankapplication.service.ManagerService;
import com.example.bankapplication.service.exception.ErrorMessage;
import com.example.bankapplication.service.exception.ManagerNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    @Transactional
    public ManagerDTO getManagerById(UUID id) {
        return managerMapper.toDTO(managerRepository.findManagerById(id).orElseThrow(
                () -> new ManagerNotFoundException(ErrorMessage.Manager_NOT_FOUND)
        ));
    }

    @Override
    public ManagerListDTO getManagersStatus() {
        return new ManagerListDTO(managerMapper.managersToManagersDTO(managerRepository.getAllByStatus(ManagerStatus.ACTIVE)));
    }

    @Override
    public ManagerDTO create(CreateManagerDTO dto) {
        var manager = managerMapper.createToEntity(dto);
        var result = managerRepository.save(manager);
        return managerMapper.toDTO(result);
    }
}
