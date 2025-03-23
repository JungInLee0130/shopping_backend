package com.example.marketapi.product.repository;

import com.example.marketapi.product.response.BuyProductResponse;
import com.example.marketapi.product.response.ProductPreviewResponse;
import com.example.marketapi.product.response.ReserveProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<ProductPreviewResponse> retrieveProductsPreview(Pageable pageable);

    Page<BuyProductResponse> retrievePurchaseProducts(Long buyerId, Pageable pageable);

    Page<ReserveProductResponse> retrieveReserveProducts(Long sellerId, Pageable pageable);
}
