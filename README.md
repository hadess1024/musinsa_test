

### 실행 방법

gradle 프로젝트 입니다.

* 빌드
  * 윈도우 : gradlew.bat build
  * 리눅스 : ./gradlew build
* 실행 파일 생성 : musinsa-0.0.1.jar

실행 환경은 다음과 같이 제공됩니다.
* default
  * H2 접속
  * java -jar musinsa-0.0.1.jar --spring.profiles.active=default


H2로 실행시

  * 기본 스키마와 데이터는 부팅 후 초기화가 됩니다. 따라서 테이블 및 테스트시 필요한 데이트는 따로 입력을 안하셔도 됩니다.
  * console로 접속하여서 정보 확인 가능합니다.

실행 포트 : 9090 포트

### H2 DB TABLE 생성 쿼리
H2 DB를 이용하는 경우 하기 쿼리를 그대로 복붙하여 실행시켜 주십시오.

```

create table Category
(
    Id           bigint auto_increment primary key,
    CategoryName varchar(64) not null,
    RegisteredAt datetime(6) not null,
    constraint UK_idlxl0cnaxo9wthlrjqy0h9es
        unique (CategoryName)
);


create table Product
(
    Id           bigint  auto_increment primary key,
    BrandId      bigint       not null,
    CategoryId   bigint       not null,
    Name         varchar(255) not null,
    Price        bigint       not null,
    RegisteredAt datetime(6)  not null
);

create table Brand
(
    Id           bigint  auto_increment  primary key,
    BrandName    varchar(64) not null,
    RegisteredAt datetime(6) not null,
    constraint UK_7gmlovw4rrg56xk95ugenn749
        unique (BrandName)
);


```
H2 접속 주소 : http://localhost:9090/h2-console

* URL - jdbc:h2:mem:testdb
* user - sa
* password - 공백
 


### 사용 기술
* JAVA 17
* Spring boot 2.7.5 버젼
* Spring MVC 기반 프레임워크


### API 명세
* 상태 코드 설명
  * 200 요청 성공
  * 201 요청 성공하여 생성 완료
  * 400	잘못된 요청 - 잘못된 매개변수
  * 404	찾을 수 없음 - 리소스가 존재하지 않습니다
  * 500	내부 서버 오류
* 실패시 응답
  * cause - 실패 유발 파라미터
    * 경우에 따라서는 이값은 NULL 일 수 있습니다.
  * message : 실패 사유

* 브랜드
  * 브랜드 추가
   ```
    * 요청
      - POST /api/v1/brands
      - 요청 본문(JSON)
        - name : 이름
    * 응답 
      - 성공(201)
      - 실패(400)
    ```
  * 브랜드 업데이트
  ```
    * 요청 
      - PUT /api/v1/brands
      - 요청 본문(JSON)
        - id : 브랜드 ID
        - name : 이름
    * 응답
      - 성공(200) 
      - 실패(200 이외)
  ```
  * 브랜드 삭제
  ```
    * 요청 
      - DELETE /api/v1/brands/{brandId}
      - brandId(PathVariable): 브랜드 ID
    * 응답
      - 성공(200)
      - 실패(200 이외)
  ``` 
* 카테고리 
  * 카테고리 생성
  ```
    * 요청 
      - POST /api/v1/categories
      - 요청 본문(JSON)
        - name : 카테고리 이름
    * 응답
      - 성공(201)
      - 실패(201 이외)
  ```
* 상품
  * 상품 추가
  ```
    * 요청
      - POST /api/v1/products
      - 요청 본문(JSON)
        - brandId : 브랜드 ID
        - name : 상품 이름
        - categoryId : 카테고리 ID
        - price : 상품 가격
    * 응답
      - 성공(201)
      - 실패(201 이외)
   ```
  * 상품 업데이트
  ```
    * 요청
      - PUT /api/v1/products
      - 요청 본문(JSON)
        - productId : 상품 ID
        - brandId : 브랜드 ID
        - name : 상품 이름
        - categoryId : 카테고리 ID
        - price : 상품 가격
    * 응답
      - 성공(200)
      - 실패(200 이외)
  ```
  * 상품 삭제
  ```
    * 요청
      - DELETE /api/v1/products/{productId}
      - 요청
        - productId(PathVariable): 상품 ID
    * 응답
      - 성공(200)
      - 실패(200 이외)
  ```
  * 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회
  ```
    * 요청
      - GET /api/v1/products/each-category/brand/price-min
    * 응답
      - 성공(200) : JSON
        - productPerCategory : 배열
          - brandName : 브랜드 이름
          - categoryName : 카테고리 이름
          - price : 가격
        - totalPrice : 총액
      - 실패(200 이외)
   ```
  * 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
      조회
  ```
    * 요청
      - GET /api/v1/products/brand-all-category/price-min
    * 응답
      - 성공(200) : JSON
        - minPrice : Object
          - category : 배열
            - categoryName : 카테고리 이름
            - price : 가격
          - brandName : 브랜드 이름
          - totalPrice : 총액
      - 실패(200 이외)
  ```
  * 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회
  ```
    * 요청
      - GET /api/v1/products/price-min-max
        - 요청 쿼리 
          - categoryName : 카테고리 이름
    * 응답
      - 성공(200) : JSON
        - categoryName : 카테고리 이름
        - minProduct : 최저가
          - brandName : 브랜드 이름
          - price : 가격
        - maxProduct : 최고가
          - brandName : 브랜드 이름
          - price : 가격
      - 실패(200 이외)
  ```

#### 프로젝트 구조

* 전체적으로 기능(서브 도메인) 중심 패키지
  * 도메인 별 레이어 아키텍쳐
    * DDD 적용  


* com.musinsa.common
  * 공통 예외 응답 및 공통 예외 처리
* com.musinsa.product
  * 상품 도메인
* com.musinsa.brand
  * 브랜드 도메인
* com.musinsa.category
  * 카테고리 도메인

  
각 모메인 별 패키지 구조
* domain
  * 엔터티, repository 인터페이스
* dto
  * 전송 객체
* exception
  * 도메인 예외 및 핸들러 
* infra
  * repository의 구현체
* service
  * 어플리케이션 서비스
* web
  * Rest api

