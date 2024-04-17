# Simple Cart Demo

## 프로젝트 생성

<https://start.spring.io/>

![Screenshot](./images/spring-initializr.png)

## 테스트 실행

```bash
./gradlew test
```

## 애플리케이션(서버) 실행

```bash
./gradlew bootRun
```

## `curl`로 간단히 테스트하기

```bash
curl -i localhost:8080

curl -i localhost:8080/cart/line-items

curl -i -X POST -H "Content-Type: application/json" \
    -d '{"productId":"product-1","quantity":2}' \
    localhost:8080/cart/line-items
```
