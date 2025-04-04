## 원티드 프리온보딩 챌린지 백엔드 20 사전과제


### 설명
사용자간 거래가 가능한 Wanted Market API를 생성해야합니다. 요구사항에 맞춰 진행해주세요. 
요구사항은 **공통**과 **1단계(필수)**, **2단계(선택)** 로 나누어져 있습니다. 

공통과 1단계는 필수로 진행해주시고, 2단계는 1단계를 마무리한 이후에 순차적으로 진행하시는 것을 추천합니다. 
스프린트를 진행하면서 기능이 어떻게 발전해나가는지 사전 과제를 통해서 체험해보시면 좋겠습니다.


<br>


### 요구사항

##### 1단계 (필수)

- [x] 1. 제품 등록과 구매는 회원만 가능합니다. 
- [x] 2. 비회원은 등록된 제품의 목록조회와 상세조회만 가능합니다. 
- [x] 3. 등록된 제품에는 "제품명", "가격", "예약상태"가 포함되어야하고, 목록조회와 상세조회시에 예약상태를 포함해야합니다.
- [x] 4. 제품의 상태는 "판매중", "예약중", "완료" 세가지가 존재합니다. 
- [x] 5. 구매자가 제품의 상세페이지에서 구매하기 버튼을 누르면 거래가 시작됩니다. 
- [x] 6. 판매자와 구매자는 제품의 상세정보를 조회하면 당사자간의 거래내역을 확인할 수 있습니다. 
- [x] 7. 모든 사용자는 내가 "구매한 용품(내가 구매자)"과 "예약중인 용품(내가 구매자/판매자 모두)"의 목록을 확인할 수 있습니다.
- [x] 8. 판매자는 거래진행중인 구매자에 대해 '판매승인'을 하는 경우 거래가 완료됩니다.


<br>

##### 2단계 (선택)
- [ ] 9. 제품에 수량이 추가됩니다. 제품정보에 "제품명", "가격", "예약상태", "수량"이 포함되어야합니다. 
- [ ] 10. 다수의 구매자가 한 제품에 대해 구매하기가 가능합니다. (단, 한 명이 구매할 수 있는 수량은 1개뿐입니다.)
- [ ] 11. 구매확정의 단계가 추가됩니다. 구매자는 판매자가 판매승인한 제품에 대해 구매확정을 할 수 있습니다. 
- [ ] 12. 거래가 시작되는 경우 수량에 따라 제품의 상태가 변경됩니다. 
    - 추가 판매가 가능한 수량이 남아있는 경우 - 판매중
    - 추가 판매가 불가능하고 현재 구매확정을 대기하고 있는 경우 - 예약중
    - 모든 수량에 대해 모든 구매자가 모두 구매확정한 경우 - 완료
- [ ] 13. "구매한 용품"과 "예약중인 용품" 목록의 정보에서 구매하기 당시의 가격 정보가 나타나야합니다. 
    - 예) 구매자 A가 구매하기 요청한 당시의 제품 B의 가격이 3000원이었고 이후에 4000원으로 바뀌었다 하더라도 목록에서는 3000원으로 나타나야합니다. 


##### 공통
0. Python이나 Java 기반의 프레임워크를 사용하시길 권장합니다. 
1. 구매취소는 고려하지 않습니다.
2. 요구사항에 모호한 부분이 많은게 맞습니다. 같은 요구사항에 대해 다양한 시각을 보여주세요. 
3. 검증이 필요한 부분에 대해 테스트코드를 작성해주세요.
4. 작성한 API에 대한 명세를 작성해주세요.
5. 개발과정에서 어려웠던 부분이나 예기치 못한 케이스가 있었다면 기록을 남겨주세요.
6. 다른분들의 PR을 보면서 리뷰를 해보세요. 궁금한점을 자유롭게 남기면서 서로의 의견을 주고 받아주세요! 
7. 요구사항을 잘 진행해주신 분들 중에서 추첨하여 선물을 드리겠습니다 :)

<br>

### 제출방법
1. 이 repository 를 fork 해주세요.
2. feature/{name} 으로 브랜치를 생성해주세요. 예: feature/suntae-kim
3. 과제를 진행해주세요.
4. 소스코드를 Push 하고 PR을 올려주세요. 
5. 요구사항에 대해서 궁금한 점이나 이해가 안되는 부분이 있다면 이슈를 남겨주시거나, 편하게 연락주세요 - kst6294@gmail.com
