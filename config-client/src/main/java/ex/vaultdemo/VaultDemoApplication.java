package ex.vaultdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class VaultDemoApplication {

    static void main(String[] args) {
        SpringApplication.run(VaultDemoApplication.class, args);
    }
}
