package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountIdDTO;
import com.example.bankapplication.entity.enums.ProductStatus;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.UUID;

public interface RequestService {
    @Transactional
    Collection<AccountIdDTO> findAccountsByProductIdAndStatus(UUID productId, ProductStatus status);
}