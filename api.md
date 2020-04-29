# API 명세

## user-service

### 1. GET /users/me
내 정보 조회
- 배송중인 상품 갯수
- 보유중인 할인쿠폰 갯수
- 잔여 마일리지
- 잔여 금액

### 2. POST /membership/register
멤버십 등록

### 3. POST /membership/unregister
멤버십 해제

### 4. POST /register
회원가입

### 5. POST /login
로그인

### 6. POST /verify
회원정보확인(회원정보 수정 전 비밀번호 묻는거)

### 7. PUT /users
내 정보 수정 **세분화될 필요 있음**

## product-service

### 1. GET /coupons/
내가 소유한 쿠폰 목록

### 2. GET /categories/recommend
카테고리 추천 (메인페이지 상단)

### 3. GET /products/recommend
상품 추천 (메인페이지 가운데)

### 4. GET /recommend
카테고리 및 상품 추천 (메인페이지 하단)

## order-service

### 1. GET /orders/
나의 주문 목록 **협의 필요**

### 2. GET /shipments/
나의 배송 목록
#### query string
type

### 3. GET /returns/
나의 반품 목록

### 4. POST /orders/{id}/shipment_start
(판매자용) 해당 주문 배송 시작

### 5. POST /orders/{id}/shipment_end
(판매자용) 해당 주문 배송 완료

## user&product-service

### 1. GET /likes/
찜한 상품 목록

## user&order-service

### 1. GET /mileages/
사용 가능 캐시,
한달 이내 소멸예정 캐시,
캐시 사용 내역

### 2. GET /reviews/