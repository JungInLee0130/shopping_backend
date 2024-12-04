package com.example.marketapi.transact.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTransactLog is a Querydsl query type for TransactLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTransactLog extends EntityPathBase<TransactLog> {

    private static final long serialVersionUID = 510690066L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransactLog transactLog = new QTransactLog("transactLog");

    public final com.example.marketapi.util.QBaseTimeEntity _super = new com.example.marketapi.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final QTransact transact;

    public final EnumPath<com.example.marketapi.transact.model.TransactState> transactState = createEnum("transactState", com.example.marketapi.transact.model.TransactState.class);

    public QTransactLog(String variable) {
        this(TransactLog.class, forVariable(variable), INITS);
    }

    public QTransactLog(Path<? extends TransactLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransactLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransactLog(PathMetadata metadata, PathInits inits) {
        this(TransactLog.class, metadata, inits);
    }

    public QTransactLog(Class<? extends TransactLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.transact = inits.isInitialized("transact") ? new QTransact(forProperty("transact"), inits.get("transact")) : null;
    }

}

