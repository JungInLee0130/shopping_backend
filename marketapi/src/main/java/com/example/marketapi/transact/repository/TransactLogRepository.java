package com.example.marketapi.transact.repository;

import com.example.marketapi.transact.entity.Transact;
import com.example.marketapi.transact.entity.TransactLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactLogRepository extends JpaRepository<TransactLog, Long> {

    List<TransactLog> findAllByTransactIn(List<Transact> transacts);
}
