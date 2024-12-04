package com.example.marketapi.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 982233604L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.example.marketapi.util.QBaseTimeEntity _super = new com.example.marketapi.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final com.example.marketapi.order.domain.QOrder order;

    public final SimplePath<com.example.marketapi.product.domain.Price> price = createSimple("price", com.example.marketapi.product.domain.Price.class);

    public final SimplePath<com.example.marketapi.product.domain.Quantity> quantity = createSimple("quantity", com.example.marketapi.product.domain.Quantity.class);

    public final EnumPath<com.example.marketapi.product.domain.Reservation> reservation = createEnum("reservation", com.example.marketapi.product.domain.Reservation.class);

    public final com.example.marketapi.member.entity.QMember seller;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.example.marketapi.order.domain.QOrder(forProperty("order"), inits.get("order")) : null;
        this.seller = inits.isInitialized("seller") ? new com.example.marketapi.member.entity.QMember(forProperty("seller"), inits.get("seller")) : null;
    }

}

