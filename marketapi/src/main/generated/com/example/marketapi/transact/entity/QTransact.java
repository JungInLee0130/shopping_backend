package com.example.marketapi.transact.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTransact is a Querydsl query type for Transact
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTransact extends EntityPathBase<Transact> {

    private static final long serialVersionUID = -724869422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransact transact = new QTransact("transact");

    public final com.example.marketapi.util.QBaseTimeEntity _super = new com.example.marketapi.util.QBaseTimeEntity(this);

    public final com.example.marketapi.member.entity.QMember buyer;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final SimplePath<com.example.marketapi.product.domain.Price> price = createSimple("price", com.example.marketapi.product.domain.Price.class);

    public final com.example.marketapi.product.entity.QProduct product;

    public QTransact(String variable) {
        this(Transact.class, forVariable(variable), INITS);
    }

    public QTransact(Path<? extends Transact> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransact(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransact(PathMetadata metadata, PathInits inits) {
        this(Transact.class, metadata, inits);
    }

    public QTransact(Class<? extends Transact> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.example.marketapi.member.entity.QMember(forProperty("buyer"), inits.get("buyer")) : null;
        this.product = inits.isInitialized("product") ? new com.example.marketapi.product.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

