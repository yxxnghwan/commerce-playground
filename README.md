# commerce-playground
커머스 도메인으로 이것 저것 연습하는 곳

## 요구사항 정리
- [x] 상품은 고유의 상품번호와 상품명, 판매가격, 재고수량 정보를 가진다.
- [x] 주문 수량이 재고 수량을 초과할 경우 `SoldOutException`이 발생한다.
- [x] 주문 금액이 5만원 미만인 경우 배송료 `2500`원이 추가된다.
- [x] 멀티 스레드 환경으로 재고 감소를 요청해서 `SoldOutException`이 정상작동하는 지 확인한다.
- [ ] 주문 요청시 외부 결제 시스템을 호출하고, 결제 처리가 완료되면 결제 id와 주문 정보를 함께 저장한다.
- [ ] 결제 시스템은 Mock으로 구현, 추후에 개발하여 추가

## 설계

### Product
상품 데이터 관리

**기능**
- 상품 목록 조회
- 상품 번호로 해당 상품을 조회
- 주문 이벤트를 구독하여 주어진 수량만큼 재고를 감소

### Order
주문 요청 처리

**기능**
- 상품 번호, 상품명, 상품 가격, 주문 수량 정보를 받아 주문 이벤트 발행
- 전체 주문 금액 계산
- 전체 주문 금액에 배송료를 포함한 결제 금액 계산
