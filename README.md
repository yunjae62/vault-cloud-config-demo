# vault-demo

Spring Cloud Config + HashiCorp Vault 데모 프로젝트.

## 디렉토리 구조

| 디렉토리 | 역할 |
|---|---|
| `config-server/` | Spring Cloud Config Server. 8888 포트. git backend로 이 레포의 `/config`를 조회 |
| `config-client/` | Config Server + Vault를 사용하는 클라이언트 앱. 8080 포트 |
| `config/` | config-server가 서빙하는 설정 파일 저장소 (`{application}.yml` 규칙) |
| `docker-compose.yml` | Vault dev 서버 정의 (8200 포트, root token 고정) |

## 실행 순서

1. Vault 기동
   ```bash
   docker-compose up -d
   ```
2. Vault에 시크릿 등록 (최초 1회)
   ```bash
   docker exec -e VAULT_ADDR=http://127.0.0.1:8200 -e VAULT_TOKEN=root vault \
     vault kv put secret/config-client foo=bar
   ```
3. config-server 기동
   ```bash
   cd config-server && ./gradlew bootRun
   ```
4. config-client 기동
   ```bash
   cd config-client && ./gradlew bootRun
   ```

`config/` 내용을 수정한 경우, config-server가 git backend로 원격 저장소를 조회하므로 커밋 + push 필요.

## 테스트 방법

- 정상 기동 확인: config-client 로그에 `message from Spring cloud config`, `foo from vault` 출력 확인
- 현재 설정 값 조회
  ```bash
  curl http://localhost:8080/config
  ```
- 무중단 갱신 확인
  1. Vault 시크릿 값 변경 (`vault kv put secret/config-client foo=updated`) 또는 `config/config-client.yml` 수정 후 push
  2. `curl -X POST http://localhost:8080/actuator/refresh`
  3. `curl http://localhost:8080/config` 재호출 → 재시작 없이 값 반영 확인
