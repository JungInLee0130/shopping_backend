## 버전정보
- spring boot 3.3
- jdk 17
- jpa
- mariadb(로컬 db)

## 요구사항
1단계 (필수)
 1. 제품 등록과 구매는 회원만 가능합니다.
 - 사용자를 User, Guest로 나누었습니다.
```java
// Role.java
public enum Role {
    User, GUEST;
}
```
- 제품 등록과 구매를 할때, 현재 사용중인 멤버의 role을 받아 api를 관리하였습니다.
```java
// 제품 등록 : 회원
@PostMapping("/add")
public ResponseEntity<Void> addProduct(Role role, ProductRequestDto productDto) {
    if (role == Role.User) {
        productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // GUEST일 경우
}

// 구매 : 회원
@PutMapping("/buy")
public ResponseEntity<Void> buyProduct(Role role, Long id) {
    if (role == Role.User) {
        productService.buyProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}
```
### 향후 개선방안
- 회원은 로그인을 해서 데이터베이스에 저장되지만, 비회원은 따로 저장된 정보가 없습니다. 따라서 회원일때만 Role.USER를 갖고, Role.GUEST는 사용지않게 될지도 모릅니다. 그래서 이에대해 처리방법을 생각해보겠습니다.
 <br><br>
 
 2. 비회원은 등록된 제품의 목록조회와 상세조회만 가능합니다.
 - 비회원은 목록조회와 상세조회만 가능하므로 이 API를 실행할때는 Role변수가 필요없습니다. 따라서 따로 처리하지않았습니다.
   ```java
   // 목록 조회 : 비회원 가능
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> getProductList() {
        return ResponseEntity.ok(productService.getProductList());
    }

    // 상세 정보 : 비회원 가능
    @GetMapping("/details")
    public ResponseEntity<ProductResponseDto> getProductDetails(Long id) {
        return ResponseEntity.ok(productService.getProductDetails(id));
    }
   ```

 
 3. 등록된 제품에는 "제품명", "가격", "예약상태"가 포함되어야하고, 목록조회와 상세조회시에 예약상태를 포함해야합니다.
 - 예약상태를 변수로 포함시켜, 해당 API 호출시 같이 출력이 됩니다.
 - Product.java
 ```java
    @Column(name = "name")
    private String name; // 제품명

    @Column(name = "price")
    private String price; // 가격

    @Column(name = "preserved")
    private Preserved preserved; // 예약상태

    @Column(name = "seller_name")
    private String sellerName; // 판매자 이름
 ```
 
 4. 제품의 상태는 "판매중", "예약중", "완료" 세가지가 존재합니다.
 - 각각 Enum으로 관리해주었습니다.
  - Preserved.java
 ```java
  public enum Preserved {
      // 판매중, 예약중, 완료
      SALE, PRESERVED, FINISH;
  }
 ```
 
 5. 구매자가 제품의 상세페이지에서 구매하기 버튼을 누르면 거래가 시작됩니다.
 - 여기서부터는 Order 도메인을 새로 생성하였습니다.
 - 거래시작 API를 호출하면, order 데이터베이스에 거래내역이 저장됩니다.
 ```java
 @GetMapping("/buy")
    public ResponseEntity<Void> orderProduct(OrderRequestDto orderRequestDto) {
        // 구매자이름 + 제품 정보 + 판매자이름을 저장
        orderService.orderProduct(orderRequestDto);

        return ResponseEntity.ok().build();
    }

 ```
 - OrderServiceImpl.class
 ```java
 public void orderProduct(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .productId(orderRequestDto.getProductId())
                .purchaserName(orderRequestDto.getPurchaserName())
                .sellerName(orderRequestDto.getSellerName())
                .build();

        orderRepository.save(order);
    }
 ```
 ### 향후 보완할점
 - 거래내역에 거래단계를 표시하기위해 Preserved를 추가해야할것같습니다. 수정해서 올리겠습니다.

 
 6. 판매자와 구매자는 제품의 상세정보를 조회하면 당사자간의 거래내역을 확인할 수 있습니다.
 - 판매자 혹은 구매자의 이름을 통해서 거래된 거래내역을 호출합니다.
 ```java
 @Override
    public OrderResponseDto orderDetails(String name) {
        Order order = orderRepository.findBySellerNameOrPurchaserName(name)
                .orElseThrow(() -> new NoSuchElementException());
        return OrderResponseDto.of(order);
    }
 ```
 

 ### 향후 보완할점
 - 제품의 상세정보이기때문에 제품번호를 통해서 호출해야합니다.
 - 제품 페이지에서 상세조회를 하기때문에 클라이언트는 제품ID를 가지고있을것입니다. 이를 파라미터로 받아서 전달하는 방법으로 변경하겠습니다.


 
 7. 모든 사용자는 내가 "구매한 용품(내가 구매자)"과 "예약중인 용품(내가 구매자/판매자 모두)"의 목록을 확인할 수 있습니다.
 

 
 8. 판매자는 거래진행중인 구매자에 대해 '판매승인'을 하는 경우 거래가 완료됩니다.
