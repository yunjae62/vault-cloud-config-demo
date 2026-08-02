package ex.vaultdemo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class VaultDemoApplication {

    @Value("${message}")
    private String message;

    @Value("${foo}")
    private String foo;

    @PostConstruct
    void init() {
        log.info("message from Spring cloud config: {}", message);
        log.info("foo from vault: {}", foo);
    }

    static void main(String[] args) {
        SpringApplication.run(VaultDemoApplication.class, args);
    }
}
