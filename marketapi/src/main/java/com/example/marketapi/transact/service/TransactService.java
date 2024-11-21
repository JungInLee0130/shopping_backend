package com.example.marketapi.transact.service;

import com.example.marketapi.transact.repository.TransactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactService {
    private final TransactRepository transactRepository;
}
