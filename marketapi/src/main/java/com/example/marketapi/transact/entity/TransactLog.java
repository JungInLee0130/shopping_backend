package com.example.marketapi.transact.entity;

import com.example.marketapi.transact.model.TransactState;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TRANSACT_LOGS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transact_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transact_id")
    private Transact transact;

    @Enumerated(EnumType.STRING)
    @Column(name = "transact_state_code")
    private TransactState transactState;

    public TransactLog(Transact transact, TransactState trasactState) {
        this.transact = transact;
        this.transactState = trasactState;
    }
}
