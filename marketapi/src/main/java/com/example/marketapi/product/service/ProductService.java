package com.example.marketapi.product.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.domain.Member;
import com.example.marketapi.member.repository.MemberRepository;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;
import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.product.entity.Product;
import com.example.marketapi.product.dto.request.ProductRequestDto;
import com.example.marketapi.product.dto.response.ProductResponseDto;
import com.example.marketapi.product.repository.ProductRepository;
import com.example.marketapi.product.response.*;
import com.example.marketapi.transact.entity.Transact;
import com.example.marketapi.transact.entity.TransactLog;
import com.example.marketapi.transact.model.TransactState;
import com.example.marketapi.transact.repository.TransactLogRepository;
import com.example.marketapi.transact.repository.TransactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final TransactRepository transactRepository;
    private final TransactLogRepository transactLogRepository;

    @Transactional
    public Long addProduct(String name, Price price, Quantity quantity, Long sellerId) {
        Member seller = memberRepository.getReferenceById(sellerId);

        Product product = new Product(seller,name,price,quantity);

        productRepository.save(product);

        return product.getId();
    }


    @Transactional
    public void buyProduct(Long productId, Long buyerId) {
        Product product = productRepository.findProductWithUpdateLockById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "상품이 없습니다.(buyproduct 오류)"));

        // 1. 판매자와 구매자가 일치하면안됨.
        validBuyer(buyerId, product);
        validBuyAlready(buyerId, product.getId()); // 이미 삼
        validQuantity(product.getQuantity()); // 수량 남아있는지

        product.purchase();

        Member buyer = memberRepository.getReferenceById(buyerId);
        Transact transact = new Transact(buyer, product);
        transactRepository.save(transact); // 거래내역 기록
    }

    // 검증
    private void validBuyer(Long buyerId, Product product) {
        if (product.getSeller().getId().equals(buyerId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "판매자와 구매자가 일치합니다.");
        }
    }

    private void validBuyAlready(Long buyerId, Long productId) {
        Optional<Transact> transacts = transactRepository.findByBuyerIdAndProductId(buyerId, productId);

        if(transacts.isPresent()){
            throw new CustomException(ErrorCode.BAD_REQUEST, "이미 구입한 상품입니다.");
        }
    }

    private void validQuantity(Quantity quantity) {
        if (Objects.isNull(quantity) || quantity.value() <= 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "물량이 없습니다.");
        }
    }

    public Page<ProductPreviewResponse> preview(Pageable pageable) {
        return productRepository.retrieveProductsPreview(pageable);
    }

    public ProductDetailResponse detail(Long productId, Optional<Long> optMemberId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (optMemberId.isEmpty()) {
            return new ProductDetailResponse(product, Collections.emptyList());
        }

        Long memberId = optMemberId.get();
        List<BuyInfo> buyInfos = getBuyInfos(product, memberId);

        return new ProductDetailResponse(product, buyInfos);
    }

    private List<BuyInfo> getBuyInfos(Product product, Long memberId) {
        Long productId = product.getId();

        if (product.getSeller().getId().equals(memberId)) {
            return getAllBuyerInfo(productId);
        }

        return getAllTransactStateInfo(productId, memberId);
    }

    private List<BuyInfo> getAllBuyerInfo(Long productId) {
        List<Transact> transacts = transactRepository.findByProductId(productId);

        Map<Long, List<TransactState>> buyerTransactStates = getBuyerTransactStates(transacts);

        return getBuyInfos(buyerTransactStates);
    }

    private LinkedHashMap<Long, List<TransactState>> getBuyerTransactStates(List<Transact> transacts) {
        return transactLogRepository.findAllByTransactIn(transacts)
                .stream()
                .sorted(Comparator.comparing(TransactLog::getId).reversed())
                .collect(Collectors.groupingBy(transactLog -> transactLog.getTransact().getBuyer().getId(),
                        LinkedHashMap::new,
                        Collectors.mapping(TransactLog::getTransactState, Collectors.toList())));
    }

    private static List<BuyInfo> getBuyInfos(Map<Long, List<TransactState>> buyerTransactStates){
        return buyerTransactStates.entrySet()
                .stream()
                .map(entry -> new PurchaseBuyerResponse(entry.getKey(), entry.getValue()))
                .map(BuyInfo.class::cast)
                .toList();
    }

    private List<BuyInfo> getAllTransactStateInfo(Long productId, Long buyerId) {
        return transactRepository.retrieveAllTransactState(buyerId, productId)
                .stream()
                .map(PurchaseDetailResponse::new)
                .map(BuyInfo.class::cast)
                .toList();
    }

    public Page<BuyProductResponse> buyProducts(Long buyerId, Pageable pageable) {
        return productRepository.retrievePurchaseProducts(buyerId, pageable);
    }

    public Page<ReserveProductResponse> reserveProducts(Long sellerId, Pageable pageable) {
        return productRepository.retrieveReserveProducts(sellerId, pageable);
    }

    public void updatePrice(Long productId, int price, Long sellerId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (!product.getSeller().getId().equals(sellerId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "NOT_SELLER");
        }

        product.updatePrice(new Price(price));
    }

    public List<ProductResponseDto> getProductList() {
        List<Product> products = productRepository.findAll();

        return ProductResponseDto.of(products);
    }


    public ProductResponseDto getProductDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "해당 제품이 없습니다."));

        return ProductResponseDto.of(product);
    }
}
