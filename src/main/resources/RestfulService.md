## REST API 설계시 고려해야 할 사항

- 개발자 중심 보다는 Consumer first

- HTTP의 장점을 최대한 살려 설계

- 리소스에 적당한 HTTP method 제공
  - GET
  - POST
  - PUT, PATCH
  - DELETE

- HTTP 상태 코드를 적절하게 처리

- URI에 중요한 데이터 노출 금지

- URI의 리소스는 단수형X, 복수형O 사용

- 리소스의 정보는 동사X, 명사O

- URI는 일괄되게 처리 -> HTTP 메소드 사용 